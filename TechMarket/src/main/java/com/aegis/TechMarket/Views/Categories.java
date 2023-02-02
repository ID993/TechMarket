package com.aegis.TechMarket.Views;


import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.AdService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@AllArgsConstructor
@RequestMapping("category")
public class Categories {

    private AdService adService;

    @Transactional
    @RequestMapping("/{category}")
    public String GetCategory(@PathVariable String category, Model model) {
        var categories = Enums.Category.values();
        var cat = Enums.Category.valueOf(category.toUpperCase().replace(" ", "_"));
        var ads = adService.findByCategoryAndStatus(cat, Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "categories";
    }

    @RequestMapping("/{category}/asc")
    public String getCategoryAsc(@PathVariable String category, Model model) {
        var categories = Enums.Category.values();
        var cat = Enums.Category.valueOf(category.toUpperCase().replace(" ", "_"));
        var ads = adService.findByCategoryAndStatusOrderByStartPriceAsc(cat, Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "categories";
    }

    @RequestMapping("/{category}/desc")
    public String getCategoryDesc(@PathVariable String category, Model model) {
        var categories = Enums.Category.values();
        var cat = Enums.Category.valueOf(category.toUpperCase().replace(" ", "_"));
        var ads = adService.findByCategoryAndStatusOrderByStartPriceDesc(cat, Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        var auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "categories";
    }

}
