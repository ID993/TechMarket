package com.aegis.TechMarket.Views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("login")
public class Login {

    @GetMapping()
    String login() {
        return "login";
    }

}