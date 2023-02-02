package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("account")
@AllArgsConstructor
public class Account {

    private final UserService userService;

    @GetMapping()
    public String getDetails(Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("auth", auth);
        return "account";
    }

    @GetMapping("/edit")
    public String getAccount(Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("auth", auth);
        return "editaccount";
    }


    @PostMapping("/edit")
    public String updateUser(Model model,
                           @ModelAttribute("user") User user,
                           HttpServletRequest request,
                           Errors errors) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userToUpdate = (User)auth.getPrincipal();
        user.setId(userToUpdate.getId());
        userService.update(user);
        return "redirect:/account";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(Model model, @PathVariable long id) {
        userService.delete(id);
        return "redirect:/logout";
    }
}
