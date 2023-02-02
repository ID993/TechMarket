package com.aegis.TechMarket.Views;


import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.AdKeywordService;
import com.aegis.TechMarket.Services.AdService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("usersads")
public class UsersAds {

    private AdService adService;
    private AdKeywordService adKeywordService;

    @GetMapping()
    @Transactional
    public String usersAds(Model model) {
        var categories = Enums.Category.values();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)auth.getPrincipal();
        var ads = adService.findByUser(user.getId());
        var dict = adService.mapAdsAndFiles(ads);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        model.addAttribute("dict", dict);
        model.addAttribute("auth", auth);
        return "usersads";
    }

    @GetMapping("delete/{id}")
    public String deleteAds(Model model, @PathVariable long id) {
        adKeywordService.delete(id);
        adService.delete(id);
        return "redirect:/usersads";
    }
}
