package org.petclinic.petclinicapp.EmulationUsers;

import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

/**
 * Класс Пользователь изменяет имя питомца
 */
public class User extends Thread {
    private RandomParameters random = new RandomParameters();
    private Clinic myClinic;

    /**
     * Конструктор
     * @param myClinic клиника
     */
    public User(Clinic myClinic) {
        this.myClinic = myClinic;
    }

    /**
     * Изменение имени питомца
     */
    public void editPetName() {
        try {
            int randomNum = random.getRandomNum();
            String petName = random.getPetName();
            myClinic.changePetName(randomNum, petName);
            System.out.println("Изменено Id: " + randomNum  + " Name: " + petName);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        } catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Переопределение метода run()
     */
    @Override
    public void run() {
        editPetName();
    }
}
