package org.petclinic.store;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
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
            Class.forName(settings.value("jdbc.driver_class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(String clientName) throws WrongInputException, IDException {
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into client (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, clientName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try (final PreparedStatement statement = this.connection.prepareStatement("update client set name=(?) where uid=(?)");) {
            statement.setString(1, clientName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException {
        try (final PreparedStatement statement = this.connection.prepareStatement("update pet set name=(?) where client_id=(?) and name=(?)");) {
            statement.setString(1, newPetName);
            statement.setInt(2, id);
            statement.setString(3, currentPetName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> searchByPetName(String petName) throws WrongInputException {
        List<Client> clients = new ArrayList<>();
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement("select client_id from pet where name=(?);");) {
            preparedStatement.setString(1, petName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(this.searchById(resultSet.getInt("client_id")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public List<Client> searchByClientName(String clientName) throws WrongInputException {
        List<Client> clients = new ArrayList<>();
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement("select * from client where name=(?)");) {
            preparedStatement.setString(1, clientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            clients = creatingClientList(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client searchById(int id) throws IDException {
        Client client = null;
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement("select * from client where uid=(?)");) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = creatingClientList(resultSet);
            if (clients.size() != 0)
                client = clients.get(0);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void delClient(int id) throws IDException {
        try (final PreparedStatement statement = this.connection.prepareStatement("delete from pet where client_id=(?); delete from client where uid=(?)");) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delPet(int id, String petName) throws WrongInputException, IDException {
        try (final PreparedStatement statement = this.connection.prepareStatement("delete from pet where client_id=(?) and name=(?)");) {
            statement.setInt(1, id);
            statement.setString(2, petName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from client")) {
            clients = creatingClientList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void removeAll() {
        try (final PreparedStatement statement = this.connection.prepareStatement("delete from pet; delete from client; ALTER SEQUENCE client_uid_seq RESTART WITH 1; ALTER SEQUENCE pet_uid_seq RESTART WITH 1");) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return false;
    }

    private List<Pet> getPets(int id) {
        final List<Pet> pets = new ArrayList<>();
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement("select * from pet where client_id=(?)");) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                pets.add(PetCreate.createPet(PetType.selectPetType(resultSet.getString("type")), resultSet.getString("name")));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    private List<Client> creatingClientList(ResultSet rs) throws SQLException {
        final List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            Client client = new Client(rs.getInt("uid"), rs.getString("name"));
            List<Pet> pets = this.getPets(rs.getInt("uid"));
            for (Pet p: pets) {
                client.addPet(p.getPetType(), p.getName());
            }
            clients.add(client);
        }
        return clients;
    }
}
