package com.aegis.TechMarket.Views;


import com.aegis.TechMarket.DataTransferObjects.UserDto;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("registration")
public class Registration {
    @Autowired
    UserService userService;

    @GetMapping()
    public String GetRegistration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @PostMapping()
    public String postRegistration(@ModelAttribute("user") UserDto userDto,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        HttpServletRequest request,
                                        Errors errors) {

        redirectAttributes.addFlashAttribute("message", "Failed username");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors() || userService.exists(userDto.getEmail())) {
            return "redirect:/registration";
        } else if (!Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())) {
            redirectAttributes.addFlashAttribute("message", "Failed password");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/registration";
        }
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        userService.create(new User(
                userDto.getFullName(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getAddress()
        ));
        return "redirect:/login";
    }

}