package org.petclinic.controllers;

import com.sun.deploy.net.HttpRequest;
import org.petclinic.models.Hibernate.Client;
import org.petclinic.models.Hibernate.Pet;
import org.petclinic.store.Hibernate.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    public String saveClient(@ModelAttribute Client client, @ModelAttribute Pet pet) {
        Set<Pet> pets = new HashSet<>();
        if (!pet.getPetName().equals(null)) {
            pet.setClient(client);
            pets.add(pet);
            client.setPets(pets);
        }
        storages.clientStorage.add(client);
        return "redirect:view";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteClient(@ModelAttribute Client client) {
        storages.clientStorage.delete(client.getId());
        return "redirect:view";
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String viewClient(@ModelAttribute Client client, ModelMap model) {
        model.addAttribute("client", storages.clientStorage.get(client.getId()));
        model.addAttribute("pets", storages.clientStorage.get(client.getId()).getPets());
        return "clinic/ClientView";
    }

    @RequestMapping(value = "/delpet", method = RequestMethod.GET)
    public String deletePet(@ModelAttribute Pet pet, @ModelAttribute Client client) {
        storages.petStorage.delete(pet.getId());
        return String.format("%s%s", "redirect:client?id=", client.getId());
    }

    @RequestMapping(value = "/addpet", method = RequestMethod.POST)
    public String savePet(@ModelAttribute Pet pet, @ModelAttribute Client client) {
        pet.setClient(client);
        storages.petStorage.add(pet);
        return String.format("%s%s", "redirect:client?id=", client.getId());
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editClientName(@ModelAttribute Client client) {
        storages.clientStorage.edit(client);
        return String.format("%s%s", "redirect:client?id=", client.getId());
    }

    @RequestMapping(value = "/editpet", method = RequestMethod.GET)
    public String editPetNameGet(@ModelAttribute Pet pet) {
        return "clinic/EditPet";
    }

    @RequestMapping(value = "/editpet", method = RequestMethod.POST)
    public String editPetName(@ModelAttribute Pet pet) {
        Pet currentPet = storages.petStorage.get(pet.getId());
        currentPet.setPetName(pet.getPetName());
        storages.petStorage.edit(currentPet);
        return String.format("%s%s", "redirect:client?id=", currentPet.getClient().getId());
    }

}
