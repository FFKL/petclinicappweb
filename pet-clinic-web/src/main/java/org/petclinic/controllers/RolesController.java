package org.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RolesController {

    @RequestMapping(value = "/login")
    public String doLogin() {
        return "login";
    }
}
