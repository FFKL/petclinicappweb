package org.petclinic.petclinicapp;

import org.junit.Test;
import org.petclinic.petclinicapp.Pets.Dog;

import static org.junit.Assert.*;

public class ClientTest {
    Dog dog = new Dog("Рекс");
    Client client = new Client(1, "Вася", dog);

    @Test
    public void testGetPet() throws Exception {
        assertEquals(dog, client.getPet());
    }

    @Test
    public void testGetClientName() throws Exception {
        assertEquals("Вася", client.getClientName());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("ID клиента: 1; Имя клиента: Вася; Имя питомца: Рекс", client.toString());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(1, client.getId());
    }
}