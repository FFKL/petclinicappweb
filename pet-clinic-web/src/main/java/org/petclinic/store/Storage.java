package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

import java.util.List;

public interface Storage {

    void add(String clientName) throws WrongInputException, IDException;

    void addPet(int id, String petType, String petName) throws IDException, WrongInputException;

    void editClientName(int id, String clientName) throws WrongInputException, IDException;

    void editPetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException;

    List<Client> searchByPetName(String petName) throws WrongInputException;

    List<Client> searchByClientName(String clientName) throws WrongInputException;

    Client searchById(int id) throws IDException;

    void delClient(int id) throws IDException;

    void delPet(int id, String petName) throws WrongInputException, IDException;

    List<Client> getClients();

    void removeAll();

    boolean isEmpty();

    void close();
}
