package org.petclinic.petclinicapp;

import org.petclinic.petclinicapp.EmulationUsers.Administrator;
import org.petclinic.petclinicapp.EmulationUsers.User;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

import java.io.IOException;

/**
 * Работа клиники.
 */
public class ClinicRunner {

    public static void main(String[] args) throws IOException, WrongInputException, IDException, PetTypeException, InterruptedException {
        Clinic myClinic = new Clinic();
        User user1 = new User(myClinic);
        User user2 = new User(myClinic);
        User user3 = new User(myClinic);
        Administrator admin1 = new Administrator(myClinic);
        Administrator admin2 = new Administrator(myClinic);
        Administrator admin3 = new Administrator(myClinic);

        admin1.start();
        admin2.start();
        admin3.start();
        user1.start();
        user2.start();
        user3.start();
        Thread.sleep(5000);
        myClinic.clientList();

        final ClinicTerminal terminal = new ClinicTerminal();
        try {
            terminal.myClinic.addClient(1, "Vasiliy Pupkin", "Dog", "Racks");
            terminal.myClinic.addClient(2, "Julia Korosteleva", "Cat", "Pushok");
            terminal.myClinic.addClient(3, "Dmitriy Pupkin", "Cat", "Hren");
        } catch (WrongInputException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        } catch (PetTypeException e) {
            e.printStackTrace();
        }
        terminal.mainMenu();
    }
}
