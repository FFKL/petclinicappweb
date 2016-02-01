package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

import java.util.List;

public interface Storage {

    public void add(int id, String clientName) throws WrongInputException, IDException;

    public void addPet(int id, String petType, String petName) throws IDException, WrongInputException;

    public void editClientName(int id, String clientName) throws WrongInputException, IDException;

    public void editPetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException;

    public List<Client> searchByPetName(String petName) throws WrongInputException;

    public List<Client> searchByClientName(String clientName) throws WrongInputException;

    public Client searchById(int id) throws IDException;

    public void delClient(int id) throws IDException;

    public void delPet(int id, String petName) throws WrongInputException, IDException;

    public List<Client> getClients();

    public void removeAll();

    public boolean isEmpty();
}
