package org.petclinic.petclinicapp;

import org.junit.Before;
import org.junit.Test;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.petclinicapp.Pets.Cat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicTest {

    Clinic myClinic = new Clinic();

    @Before
    public void setUp() throws Exception {
        myClinic.addClient(1, "Vasya");
        myClinic.addPetForClient(1, "Cat", "Pushok");
        myClinic.addClient(2, "Fedor");
        myClinic.addPetForClient(2, "Cat", "Pushok");
        myClinic.addPetForClient(2, "Dog", "Bull");
        myClinic.addClient(3, "Grisha");
    }

    @Test
    public void testAddClient() throws Exception {
        assertFalse(myClinic.isEmpty());
    }

    @Test
    public void testAddPetForClient() throws Exception {
        assertFalse(myClinic.findById(1).getPets().isEmpty());
    }

    @Test
    public void testFindClientsByPetName() throws  Exception {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(1, "Vasya");
        client1.addPet("Cat", "Pushok");
        Client client2 = new Client(2, "Fedor");
        client2.addPet("Cat", "Pushok");
        client2.addPet("Dog", "Bull");
        clients.add(client1);
        clients.add(client2);

        assertEquals(myClinic.findClientsByPetName("Pushok"), clients);
    }

    @Test
    public void testFindByClientName() throws Exception {
        List<Client> clients = new ArrayList<>();
        Client client2 = new Client(2, "Fedor");
        client2.addPet("Cat", "Pushok");
        client2.addPet("Dog", "Bull");
        clients.add(client2);
        assertEquals(myClinic.findByClientName("Fedor"), clients);
    }

    @Test
    public void testFindById() throws Exception {
        Client client2 = new Client(2, "Fedor");
        client2.addPet("Cat", "Pushok");
        client2.addPet("Dog", "Bull");

        assertEquals(myClinic.findById(2),client2);
    }

    @Test
    public void testChangeClientName() throws Exception {
        myClinic.changeClientName(1, "Fedor");

        assertEquals(myClinic.findById(1).getClientName(), "Fedor");
    }

    @Test
    public void testChangePetName() throws Exception {
        myClinic.changePetName(2,"Pushok", "Snezhok");

        assertEquals(myClinic.findById(2).getPets().get(0).getName(), "Snezhok");
    }

    @Test
    public void testRemovePet() throws Exception {
        assertEquals(myClinic.findById(2).getPets().size(), 2);
        myClinic.removePet(2, "Bull");
        assertEquals(myClinic.findById(2).getPets().size(), 1);
    }

    @Test
    public void testRemoveClient() throws Exception {
        assertEquals(myClinic.getClients().size(), 3);
        myClinic.removeClient(3);
        assertEquals(myClinic.getClients().size(), 2);
    }

    @Test
    public void testGetClients() throws Exception {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1, "Vasya"));
        clients.get(0).addPet("Cat", "Pushok");
        clients.add(new Client(2, "Fedor"));
        clients.get(1).addPet("Cat", "Pushok");
        clients.get(1).addPet("Dog", "Bull");
        clients.add(new Client(3, "Grisha"));

        assertEquals(myClinic.getClients(), clients);
    }

    @Test
    public void testIsEmpty() throws Exception {
        myClinic.removeAll();
        assertTrue(myClinic.isEmpty());
    }
}