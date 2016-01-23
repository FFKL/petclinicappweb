package org.petclinic.store;

import org.junit.Test;
import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Clinic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicCacheTest {

    @Test
    public void testGetInstance() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        assertEquals(instance, ClinicCache.getInstance());
    }

    @Test
    public void testAdd() throws Exception {

        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.addClient(2, "Grigoriy");
        clinic.addPetForClient(2, "Dog", "Sharik");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Grigoriy");
        instance.addPet(2, "Dog", "Sharik");

        assertEquals(clinic.getClients(), instance.getClients());
        instance.removeAll();
    }

    @Test
    public void testEditClientName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.changeClientName(1, "Ivan");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.editClientName(1, "Ivan");

        assertEquals(clinic.getClients().get(0), instance.getClients().get(0));
        instance.removeAll();
    }

    @Test
    public void testEditPetName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.changePetName(1, "Pushok", "Snezhok");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.editPetName(1, "Pushok", "Snezhok");

        assertEquals(clinic.getClients().get(0), instance.getClients().get(0));
        instance.removeAll();
    }

    @Test
    public void testSearchByPetName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.addClient(2, "Grigoriy");
        clinic.addPetForClient(2, "Dog", "Sharik");
        List<Client> clients = clinic.findClientsByPetName("Pushok");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Grigoriy");
        instance.addPet(2, "Cat", "Sharik");
        List<Client> instClients = instance.searchByPetName("Pushok");

        assertEquals(clients, instClients);
        instance.removeAll();
    }

    @Test
    public void testSearchByClientName() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.addClient(2, "Fedor");
        clinic.addPetForClient(2, "Dog", "Sharik");
        List<Client> clients = clinic.findByClientName("Fedor");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Fedor");
        instance.addPet(2, "Dog", "Sharik");
        List<Client> instClients = instance.searchByClientName("Fedor");

        assertEquals(clients, instClients);
        instance.removeAll();
    }

    @Test
    public void testSearchById() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.addClient(2, "Fedor");
        clinic.addPetForClient(2, "Dog", "Sharik");
        Client client = clinic.findById(2);

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Fedor");
        instance.addPet(2, "Dog", "Sharik");
        Client instClient = instance.searchById(2);

        assertEquals(client, instClient);
        instance.removeAll();
    }

    @Test
    public void testDelClient() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();

        Clinic clinic = new Clinic();
        clinic.addClient(1, "Fedor");
        clinic.addPetForClient(1, "Cat", "Pushok");
        clinic.addClient(2, "Fedor");
        clinic.addPetForClient(2, "Dog", "Sharik");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Fedor");
        instance.addPet(2, "Dog", "Sharik");

        clinic.removeClient(1);
        instance.delClient(1);
        assertEquals(clinic.getClients(), instance.getClients());
        instance.removeAll();
    }

    @Test
    public void testGetClients() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(1, "Fedor"));
        clients.get(0).addPet("Cat", "Pushok");
        clients.add(new Client(2, "Fedor"));
        clients.get(1).addPet("Dog", "Sharik");

        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Fedor");
        instance.addPet(2, "Dog", "Sharik");

        assertEquals(clients, instance.getClients());
        instance.removeAll();
    }

    @Test
    public void testRemoveAll() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();
        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        instance.add(2, "Fedor");
        instance.addPet(2, "Dog", "Sharik");
        instance.removeAll();
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testRemovePet() throws Exception {
        ClinicCache instance = ClinicCache.getInstance();
        instance.add(1, "Fedor");
        instance.addPet(1, "Cat", "Pushok");
        assertFalse(instance.searchById(1).getPets().isEmpty());
        instance.delPet(1, "Pushok");
        assertTrue(instance.searchById(1).getPets().isEmpty());
        instance.removeAll();
    }
}