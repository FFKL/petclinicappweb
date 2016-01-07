package org.petclinic.petclinicapp;

import org.junit.Test;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.petclinicapp.Pets.Dog;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicTest {

    Clinic myClinic = new Clinic();

    @Test
    public void testFindClientsByPetName() throws Exception {
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(2, "Vasiliy", new Cat("Cat")));

        myClinic.addClient(1, "Vasiliy", "Dog", "Bim");
        myClinic.addClient(2, "Vasiliy", "Cat", "Cat");
        myClinic.addClient(3, "Dmitriy", "Cat", "Vaska");

        assertEquals(clients, myClinic.findClientsByPetName("Cat"));
    }

    @Test
    public void testFindByClientName() throws Exception {
        Clinic testClinic = new Clinic();
        testClinic.addClient(1, "Vasiliy", "Dog", "Bim");
        testClinic.addClient(2, "Vasiliy", "Cat", "Cat");
        testClinic.addClient(3, "Dmitriy", "Cat", "Vaska");

        myClinic.addClient(1, "Vasiliy", "Dog", "Bim");
        myClinic.addClient(2, "Vasiliy", "Cat", "Cat");
        myClinic.addClient(3, "Dmitriy", "Cat", "Vaska");

        assertEquals(testClinic.findByClientName("Vasiliy"), myClinic.findByClientName("Vasiliy"));
    }

    @Test(expected = WrongInputException.class)
    public void testAddClientWIException() throws WrongInputException, IDException, PetTypeException {
        Clinic myClinic = new Clinic();
        myClinic.addClient(3, "Dmitriy333", "Cat", "Vaska");
    }

    @Test(expected = IDException.class)
    public void testAddClientIDException() throws WrongInputException, IDException, PetTypeException {
        Clinic myClinic = new Clinic();
        myClinic.addClient(3, "Fedor", "Cat", "Vaska");
        myClinic.addClient(3, "Dmitriy", "Cat", "Pushok");
    }

    @Test(expected = WrongInputException.class)
    public void testFindClientsByPetName1() throws WrongInputException {
        myClinic.findClientsByPetName("132Pushok");
    }

    @Test(expected = WrongInputException.class)
    public void testFindByClientNameEx() throws WrongInputException {
        myClinic.findByClientName("123Vasiliy");
    }

    @Test(expected = WrongInputException.class)
    public void testChangeClientNameEx() throws WrongInputException, IDException {
        myClinic.changeClientName(2, "123Vasiliy");
    }

    @Test(expected = WrongInputException.class)
    public void testChangePetName() throws WrongInputException, IDException {
        myClinic.changePetName(3, "Pushok32");
    }
}