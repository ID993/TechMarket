package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.DataTransferObjects.AdDto;
import com.aegis.TechMarket.DataTransferObjects.MailDto;
import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Entities.Alarm;
import com.aegis.TechMarket.Entities.Keyword;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
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

import java.io.IOException;
import java.util.HashSet;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("createad")
public class CreateAd {

    private AdService adService;
    private FileService fileService;
    private AdKeywordService adKeywordService;
    private AlarmService alarmService;
    private AlarmKeywordService alarmKeywordService;


    private boolean containsAllKeywords(List<Keyword> keywordsAd, List<Keyword> keywordsAlarm){
        return new HashSet<>(keywordsAlarm).containsAll(keywordsAd);
    }

    @Async
    public void sendNotification(Ad ad) {
        var mail = new MailDto();
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var alarmList = alarmService.findAll();

        var keywordsAd = adKeywordService.getKeywordsByAd(ad.getId());
        alarmList.stream().filter(alarm -> alarm.getUser().getId() != user.getId()
                && alarm.getMinPrice() <= ad.getStartPrice()
                && alarm.getMaxPrice() >= ad.getStartPrice()
                && alarm.getCategory().equals(ad.getCategory())
                && containsAllKeywords(keywordsAd,alarmKeywordService.getKeywordsByAlarm(alarm.getId()))
        ).forEach(alarm -> {
            mail.setTo(alarm.getUser().getEmail());
            mail.setSubject("Your alarm has been activated!");
            mail.setMessage("Hi, your alarm: <b>" + alarm.getName() + "</b> has been activated because " +
                    "the ad according to your criteria has arrived! " +
                    "Check the ad by name: <b>" + ad.getName() + "</b>!");
            try {
                alarmService.sendRequest(mail);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }



    @GetMapping()
    public String getCreation(WebRequest request, Model model) {
        var ad = new AdDto();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        MultipartFile[] images = null;
        var category = Enums.Category.values();
        model.addAttribute("ad", ad);
        model.addAttribute("images", images);
        model.addAttribute("category", category);
        model.addAttribute("auth", auth);
        return "createad";
    }


    @PostMapping()
    public String adCreation(Model model,
               @ModelAttribute("ad") AdDto adDto,
               @ModelAttribute("images") MultipartFile[] images,
               @ModelAttribute("category") Enums.Category category,
               @RequestParam("keywords") String keywords,
               RedirectAttributes redirectAttributes,
               HttpServletRequest request,
               Errors errors) throws IOException, MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = auth.getPrincipal();
        adDto.setUser((User)user);
        adDto.setStatus(Enums.Status.Active);
        var newAd = adService.create(adDto);
        adKeywordService.saveMultiple(newAd.getId(), keywords);
        sendNotification(newAd);
        fileService.storeMultiple(newAd.getId(), images);
        return "redirect:/ad/details/" + newAd.getId();
    }
}
