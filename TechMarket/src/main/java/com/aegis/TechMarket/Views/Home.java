package com.aegis.TechMarket.Views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/home")
    public String GetHome(){
        return "index";
    }
}