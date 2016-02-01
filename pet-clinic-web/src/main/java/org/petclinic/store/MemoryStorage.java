package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

import java.util.List;

public class MemoryStorage implements Storage {

    private static final Clinic CLINIC = new Clinic();

    private static final ClinicCache INSTANCE = new ClinicCache();

    public static ClinicCache getInstance() {
        return INSTANCE;
    }

    public void add(int id, String clientName) throws WrongInputException, IDException {
        CLINIC.addClient(id, clientName);
    }

    public void addPet(int id, String petType, String petName) throws IDException, WrongInputException {
        CLINIC.addPetForClient(id, petType, petName);
    }

    public void editClientName(int id, String clientName) throws WrongInputException, IDException {
        CLINIC.changeClientName(id, clientName);
    }

    public void editPetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException {
        CLINIC.changePetName(id, currentPetName, newPetName);
    }

    public List<Client> searchByPetName(String petName) throws WrongInputException {
        return CLINIC.findClientsByPetName(petName);
    }

    public List<Client> searchByClientName(String clientName) throws WrongInputException {
        return CLINIC.findByClientName(clientName);
    }

    public Client searchById(int id) throws IDException {
        return CLINIC.findById(id);
    }

    public void delClient(int id) throws IDException {
        CLINIC.removeClient(id);
    }

    public void delPet(int id, String petName) throws WrongInputException, IDException {
        CLINIC.removePet(id, petName);
    }

    public List<Client> getClients() {
        return CLINIC.getClients();
    }

    public void removeAll() {
        CLINIC.removeAll();
    }

    public boolean isEmpty() {
        return CLINIC.isEmpty();
    }
}