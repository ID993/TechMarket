package com.aegis.TechMarket.Views;

import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.AdService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/")
public class Index {

    private AdService adService;

    @Transactional
    @GetMapping()
    public String getIndex(Model model) {
        var categories = Enums.Category.values();
        var ads = adService.findByStatus(Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "index";
    }

    @Transactional
    @GetMapping("/asc")
    public String getIndexAsc(Model model) {
        var categories = Enums.Category.values();
        var ads = adService.findByStatusOrderByStartPriceAsc(Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "index";
    }

    @Transactional
    @GetMapping("/desc")
    public String getIndexDesc(Model model) {
        var categories = Enums.Category.values();
        var ads = adService.findByStatusOrderByStartPriceDesc(Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "index";
    }

}