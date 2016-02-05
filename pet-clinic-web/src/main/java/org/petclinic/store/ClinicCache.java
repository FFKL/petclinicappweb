package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

import java.util.List;

public class ClinicCache implements Storage {

    private static final ClinicCache INSTANCE = new ClinicCache();

    private Storage storage = new JdbcStorage();

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public static ClinicCache getInstance() {
        return INSTANCE;
    }

    public void add(String clientName) throws WrongInputException, IDException {
        this.storage.add(clientName);
    }

    public void addPet(int id, String petType, String petName) throws IDException, WrongInputException {
        this.storage.addPet(id, petType, petName);
    }

    public void editClientName(int id, String clientName) throws WrongInputException, IDException {
        this.storage.editClientName(id, clientName);
    }

    public void editPetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException {
        this.storage.editPetName(id, currentPetName, newPetName);
    }

    public List<Client> searchByPetName(String petName) throws WrongInputException {
        return this.storage.searchByPetName(petName);
    }

    public List<Client> searchByClientName(String clientName) throws WrongInputException {
        return this.storage.searchByClientName(clientName);
    }

    public Client searchById(int id) throws IDException {
        return this.storage.searchById(id);
    }

    public void delClient(int id) throws IDException {
        this.storage.delClient(id);
    }

    public void delPet(int id, String petName) throws WrongInputException, IDException {
        this.storage.delPet(id, petName);
    }

    public List<Client> getClients() {
        return this.storage.getClients();
    }

    public void removeAll() {
        this.storage.removeAll();
    }

    public boolean isEmpty() {
        return this.storage.isEmpty();
    }
}
