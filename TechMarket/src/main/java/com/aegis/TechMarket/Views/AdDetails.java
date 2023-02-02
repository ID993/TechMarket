package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.DataTransferObjects.MailDto;
import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


@Controller
@RequestMapping("ad/details")
@AllArgsConstructor
public class AdDetails {

    private AdService adService;

    private FileService fileService;

    private AdKeywordService adKeywordService;

    private AlarmService alarmService;

    @GetMapping("/{id}")
    public String getDetails(@PathVariable("id") long id, Model model) {
        var mailForm = new MailDto();
        var ad = adService.findById(id);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        mailForm.setTo(ad.getUser().getEmail());
        mailForm.setSubject(ad.getName());

        model.addAttribute("ad", ad);
        model.addAttribute("files", fileService.getByAd(ad));
        model.addAttribute("mailForm", mailForm);
        model.addAttribute("auth", auth);
        return "adDetails";
    }

    @GetMapping("/{id}/images/{filename}")
    public ResponseEntity<?> getAdDetailsImages(@PathVariable long id, @PathVariable String filename) throws IOException {
        File file = new File("src/main/resources/static/images/" + filename);
        byte[] data = FileUtils.readFileToByteArray(file);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(data.length)
                .body(resource);
    }


    @GetMapping("/{id}/edit")
    public String editAd(@PathVariable long id, WebRequest request, Model model){
        var ad = adService.findById(id);
        model.addAttribute("ad", ad);
        model.addAttribute("files", fileService.getByAd(ad));
        model.addAttribute("category", Enums.Category.values());
        model.addAttribute("status", Enums.Status.values());
        return "editad";
    }

    @PostMapping("/{id}/edit")
    public String updateAd(Model model,
                             @ModelAttribute("ad") Ad ad,
                             @ModelAttribute("files") MultipartFile[] files,
                             @ModelAttribute("category") Enums.Category category,
                             @ModelAttribute("status") Enums.Status status,
                             @RequestParam("keywords") String keywords,
                             HttpServletRequest request,
                             Errors errors) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = auth.getPrincipal();
        ad.setUser((User) user);
        var newAd = adService.update(ad);
        adKeywordService.saveMultiple(newAd.getId(), keywords);
        fileService.storeMultiple(newAd.getId(), files);
        return "redirect:/ad/details/" + ad.getId();
    }


    @GetMapping("{id}/edit/images/{filename}/delete")
    public String deleteImages(@PathVariable long id, @PathVariable String filename) throws IOException {
        var file = fileService.getByAdAndName(adService.findById(id), filename);
        fileService.delete(file);
        return "deletion";
    }


    @PostMapping("{id}/send-request")
    public String sendRequest(@PathVariable long id,
                                        @ModelAttribute ("mailForm") MailDto mailDto,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes
                                        ) throws MessagingException {
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var from = adService.findById(id).getUser().getEmail();
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors() || SecurityContextHolder.getContext().getAuthentication() == null) {
            return "redirect:/ad/details/{id}";
        }
        else if (Objects.equals(from, user.getEmail())){
            redirectAttributes.addFlashAttribute("message", "Failed user");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/ad/details/{id}";
        }

        alarmService.sendRequest(mailDto);
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/ad/details/{id}";
    }
}