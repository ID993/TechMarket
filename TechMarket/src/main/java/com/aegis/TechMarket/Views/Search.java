package com.aegis.TechMarket.Views;


import com.aegis.TechMarket.Entities.User;
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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
@RequestMapping("search")
public class Search {

    private AdService adService;

    @RequestMapping()
    @Transactional
    public String search(@RequestParam String name, Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var ads = adService.searchByStatus(name, Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        model.addAttribute("dict", dict);
        model.addAttribute("categories", Enums.Category.values());
        model.addAttribute("auth", auth);
        return "search";
    }

    @RequestMapping("/usersads/{userId}")
    public String searchUsersAds(@PathVariable long userId, @RequestParam String name, Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)auth.getPrincipal();
        var ads = adService.searchByUser(name, user.getId());
        var dict = adService.mapAdsAndFiles(ads);
        model.addAttribute("dict", dict);
        model.addAttribute("categories", Enums.Category.values());
        model.addAttribute("auth", auth);
        return "search";
    }

    @RequestMapping("/categories/{category}")
    @Transactional
    public String searchCategoryAds(@PathVariable String category, @RequestParam String name, Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var cat = Enums.Category.valueOf(category.toUpperCase().replace(" ", "_"));
        var ads = adService.searchByCategoryAndStatus(name, cat, Enums.Status.Active);
        var dict = adService.mapAdsAndFiles(ads);
        model.addAttribute("dict", dict);
        model.addAttribute("categories", Enums.Category.values());
        model.addAttribute("auth", auth);
        return "search";
    }

}