package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.petclinicapp.Pets.Pet;
import org.petclinic.petclinicapp.Pets.PetCreate;
import org.petclinic.petclinicapp.Pets.PetType;
import org.petclinic.service.Settings;

import java.sql.*;
import java.util.ArrayList;
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
    //// TODO: 02.02.2016 rework generate id
    public void add(int id, String clientName) {
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into client (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, clientName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //throw new IllegalStateException("Could not create new user");

    }

    public void addPet(int id, String petType, String petName) throws IDException, WrongInputException {
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into pet (client_id, name, type) values (?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, petName);
            statement.setString(3, petType);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        final List<Client> clients = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from client")) {
            while (rs.next()) {
                Client client = new Client(rs.getInt("uid"), rs.getString("name"));
                List<Pet> pets = this.getPets(rs.getInt("uid"));
                for (Pet p: pets) {
                    client.addPet(p.getPetType(), p.getName());
                }
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void removeAll() {

    }

    public boolean isEmpty() {
        return false;
    }

    public List<Pet> getPets(int id) {
        final List<Pet> pets = new ArrayList<>();
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement("select * from pet where client_id=(?)");) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next())
                pets.add(PetCreate.createPet(PetType.selectPetType(resultSet.getString("type")), resultSet.getString("name")));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }
}
