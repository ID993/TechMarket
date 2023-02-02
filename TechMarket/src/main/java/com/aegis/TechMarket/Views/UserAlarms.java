package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.Entities.Alarm;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.AlarmKeywordService;
import com.aegis.TechMarket.Services.AlarmService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("usersalarms")
public class UserAlarms {

    private AlarmService alarmService;

    private AlarmKeywordService alarmKeywordService;

    @GetMapping()
    @Transactional
    public String usersAlarms(Model model) {
        var categories = Enums.Category.values();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)auth.getPrincipal();
        var alarms = alarmService.findAllByUser(user);
        model.addAttribute("categories", categories);
        model.addAttribute("alarms", alarms);
        model.addAttribute("auth", auth);
        return "usersalarms";
    }


    @GetMapping("edit/{id}")
    public String editAlarm(@PathVariable long id, WebRequest request, Model model){
        var alarm = alarmService.findById(id);
        var auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("alarm", alarm);
        model.addAttribute("category", Enums.Category.values());
        model.addAttribute("auth", auth);
        return "editalarm";
    }

    @PostMapping("edit/{id}")
    public String updateAd(Model model,
                           @ModelAttribute("alarm") Alarm alarm,
                           @ModelAttribute("category") Enums.Category category,
                           @RequestParam("keywords") String keywords,
                           HttpServletRequest request,
                           Errors errors) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = auth.getPrincipal();
        alarm.setUser((User) user);
        var newAlarm = alarmService.update(alarm);
        alarmKeywordService.saveAlarmMultipleKeywords(newAlarm.getId(), keywords);
        return "redirect:/usersalarms";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlarm(Model model, @PathVariable long id) {
        //alarmKeywordService.delete(id);
        alarmService.deleteById(id);
        return "redirect:/usersalarms";
    }


}
