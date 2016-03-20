package org.petclinic.controllers;

import org.petclinic.models.Hibernate.Client;
import org.petclinic.models.Hibernate.Pet;
import org.petclinic.store.Hibernate.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/clinic")
public class ClinicViewController {
    @Autowired
    private Storages storages;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String showClinic(ModelMap model) {
        model.addAttribute("clients", storages.clientStorage.values());
        return "clinic/ClinicView";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String saveClientGet() {
        return "clinic/AddClient";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute Client client, @ModelAttribute Pet pet, ModelMap model) {
        System.out.println(pet);
        Set<Pet> pets = new HashSet<>();
        if (!pet.getPetName().equals(null)) {
            pet.setClient(client);
            pets.add(pet);
            client.setPets(pets);
        }
        System.out.println(client);
        storages.clientStorage.add(client);
        return "redirect:view";
    }
}
