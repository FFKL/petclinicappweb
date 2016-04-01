package org.petclinic.controllers;

import org.petclinic.store.Hibernate.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RolesController {

    @Autowired
    private Storages storages;

    @RequestMapping(value = "/login")
    public String doLogin() {
        return "login";
    }

    @RequestMapping(value = "/superadmin", method = RequestMethod.GET)
    public String showAdminPanel(ModelMap model) {
        model.addAttribute("users", storages.userStorage.values());
        return "superadmin/SecurityPanel";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
