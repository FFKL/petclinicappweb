package org.petclinic.petclinicapp;

import org.junit.Before;
import org.junit.Test;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.petclinicapp.Pets.DefaultPet;
import org.petclinic.petclinicapp.Pets.Dog;
import org.petclinic.petclinicapp.Pets.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class ClientTest {

    Client client = new Client(1, "Vasya");

    @Test
    public void testGetPets() throws Exception {
        client.addPet("Cat", "Pushok");
        client.addPet("Dog", "Bull");
        client.addPet(" ", "Red");

        List<Pet> pets = new ArrayList<>();
        pets.add(new Cat("Pushok"));
        pets.add(new Dog("Bull"));
        pets.add(new DefaultPet("Red"));

        assertEquals(client.getPets(), pets);
    }

    @Test
    public void testGetClientName() throws Exception {
        assertEquals(client.getClientName(), "Vasya");
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(client.getId(), 1);
    }

    @Test
    public void testRemovePet() throws Exception {
        client.addPet("Cat", "Pushok");
        client.removePet("Pushok");

        assertTrue(client.getPets().isEmpty());
    }

    @Test
    public void testSetClientName() throws Exception {
        client.setClientName("Vasya");

        assertEquals(client, new Client(1, "Vasya"));
    }

    @Test
    public void testRemoveAll() throws Exception {
        client.addPet("Cat", "Ryzhik");
        client.addPet("Dog", "Bull");
        assertFalse(client.getPets().isEmpty());
        client.removeAll();
        assertTrue(client.getPets().isEmpty());
    }

    @Test
    public void testToString() throws Exception {
        client.addPet("Cat", "Pushok");
        assertEquals(client.toString(), "ID: 1; ClientName: Vasya; PetList: [PetType: Cat; PetName: Pushok]");
    }

    @Test
    public void testEquals() throws Exception {
        Client client1 = new Client(2, "Vasya");
        Client client2 = new Client(2, "Vasya");
        Client client3 = null;
        Object object = new Object();

        assertEquals(client1, client2);

        assertFalse(client1.equals(client3));
        assertFalse(client1.equals(object));
        assertTrue(client1.equals(client1));
    }

    @Test
    public void testHashCode() throws Exception {
        Client client2 = new Client(1, "Vasya");

        assertEquals(client.hashCode(), client2.hashCode());

    }
}