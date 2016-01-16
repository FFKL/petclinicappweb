package org.petclinic.petclinicapp;

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
        try {
            myClinic.addClient(1, "Vasiliy Pupkin", "Dog", "Racks");
            myClinic.addClient(2, "Julia Korosteleva", "Cat", "Pushok");
            myClinic.addClient(3, "Dmitriy Pupkin", "Cat", "Hren");
        } catch (WrongInputException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        } catch (PetTypeException e) {
            e.printStackTrace();
        }
    }
}
