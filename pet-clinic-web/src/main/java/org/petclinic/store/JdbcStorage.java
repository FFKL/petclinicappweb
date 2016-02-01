package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.service.Settings;

import java.sql.*;
import java.util.List;

public class JdbcStorage implements Storage {
    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(int id, String clientName) {

    }

    public void addPet(int id, String petType, String petName) throws IDException, WrongInputException {

    }

    public void editClientName(int id, String clientName) throws WrongInputException, IDException {

    }

    public void editPetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException {

    }

    public List<Client> searchByPetName(String petName) throws WrongInputException {
        return null;
    }

    public List<Client> searchByClientName(String clientName) throws WrongInputException {
        return null;
    }

    public Client searchById(int id) throws IDException {
        return null;
    }

    public void delClient(int id) throws IDException {

    }

    public void delPet(int id, String petName) throws WrongInputException, IDException {

    }

    public List<Client> getClients() {
        return null;
    }

    public void removeAll() {

    }

    public boolean isEmpty() {
        return false;
    }
}
