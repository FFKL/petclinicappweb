package org.petclinic.store;

import org.junit.Test;
import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.petclinicapp.Pets.Dog;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicCacheTest {

    /*@Test
    public void testGetInstance() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        assertEquals(instance, ClinicCache.getInstance());
    }

    @Test
    public void testAdd() throws Exception {

        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.addClient(2, "Grigoriy", "Dog", "Sharik");

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Grigoriy", "Dog", "Sharik");

        assertEquals(clinic.getClients(), instance.getClients());
        instance.removeAll();
    }

    @Test
    public void testEditClientName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.changeClientName(1, "X");

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.editClientName(1, "X");

        assertEquals(clinic.getClients().get(0), instance.getClients().get(0));
        instance.removeAll();
    }

    @Test
    public void testEditPetName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.changePetName(1, "X");

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.editPetName(1, "X");

        assertEquals(clinic.getClients().get(0), instance.getClients().get(0));
        instance.removeAll();
    }

    @Test
    public void testSearchByPetName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.addClient(2, "Grigoriy", "Dog", "Sharik");
        List<Client> clients = clinic.findClientsByPetName("Pushok");

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Grigoriy", "Cat", "Sharik");
        List<Client> instClients = instance.searchByPetName("Pushok");

        assertEquals(clients, instClients);
        instance.removeAll();
    }

    @Test
    public void testSearchByClientName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.addClient(2, "Fedor", "Dog", "Sharik");
        List<Client> clients = clinic.findByClientName("Fedor");

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Fedor", "Dog", "Sharik");
        List<Client> instClients = instance.searchByClientName("Fedor");

        assertEquals(clients, instClients);
        instance.removeAll();
    }

    @Test
    public void testSearchById() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.addClient(2, "Fedor", "Dog", "Sharik");
        Client client = clinic.findById(2);

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Fedor", "Dog", "Sharik");
        Client instClient = instance.searchById(2);

        assertEquals(client, instClient);
        instance.removeAll();
    }

    @Test
    public void testDelClient() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor", "Cat", "Pushok");
        clinic.addClient(2, "Fedor", "Dog", "Sharik");

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Fedor", "Dog", "Sharik");

        clinic.removeClient(1);
        instance.delClient(1);
        assertEquals(clinic.getClients(), instance.getClients());
        instance.removeAll();
    }

    @Test
    public void testGetClients() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(1, "Fedor", new Cat("Pushok")));
        clients.add(new Client(2, "Fedor", new Dog("Sharik")));

        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Fedor", "Dog", "Sharik");

        assertEquals(clients, instance.getClients());
        instance.removeAll();
    }

    @Test
    public void testRemoveAll() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();
        instance.add(1, "Fedor", "Cat", "Pushok");
        instance.add(2, "Fedor", "Dog", "Sharik");
        instance.removeAll();
        assertTrue(instance.isEmpty());
    }*/
}