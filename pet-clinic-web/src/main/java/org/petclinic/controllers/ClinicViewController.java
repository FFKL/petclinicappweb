package org.petclinic.controllers;

import org.petclinic.store.Hibernate.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clinic")
public class ClinicViewController {
    @Autowired
    private Storages storages;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String showRoles(ModelMap model) {

        model.addAttribute("clients", storages.clientStorage.values());
        return "clinic/view";
    }
}
