package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.Entities.Alarm;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import java.io.IOException;


@AllArgsConstructor
@Controller
@RequestMapping("createalarm")
public class CreateAlarm {

    private AlarmService alarmService;
    private AlarmKeywordService alarmKeywordService;


    @GetMapping()
    public String createAlarm(WebRequest request, Model model) {
        var alarm = new Alarm();
        var category = Enums.Category.values();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("alarm", alarm);
        model.addAttribute("category", category);
        model.addAttribute("auth", auth);
        return "createalarm";
    }

    @PostMapping()
    public String AdCreation(Model model,
                             @ModelAttribute("alarm") Alarm alarm,
                             @ModelAttribute("category") Enums.Category category,
                             @RequestParam("keywords") String keywords,
                             HttpServletRequest request,
                             Errors errors) throws IOException {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = auth.getPrincipal();
        alarm.setUser((User)user);
        var newAlarm = alarmService.create(alarm);
        alarmKeywordService.saveAlarmMultipleKeywords(newAlarm.getId(), keywords);
        return "redirect:/usersalarms";
    }
}
