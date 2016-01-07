package org.petclinic.petclinicapp.EmulationUsers;

import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

/**
 * Класс Администратор два раза добавляет пользователя и один раз удаляет
 */
public class Administrator extends Thread {
    private RandomParameters random = new RandomParameters();
    private Clinic myClinic;

    /**
     * Конструктор
     * @param myClinic клиника
     */
    public Administrator(Clinic myClinic) {
        this.myClinic = myClinic;
    }

    /**
     * Добавление пользователя в клинику
     */
    public void addUser() {
        try {
            int id = random.getId();
            String clientName = random.getClientName();
            String petType = random.getPetType();
            String petName = random.getPetName();
            this.myClinic.addClient(id, clientName, petType, petName);
            System.out.println("Добавлено Id: " + id + " клиент: " + clientName + " Тип: " + petType + " питомец: " + petName);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        } catch (IDException e) {
            System.out.println(e.getMessage());
        } catch (PetTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаление пользователя из клиники
     */
    public void removeUser() {
        try {
            int id = random.getRandomNum();
            this.myClinic.removeClient(id);
            System.out.println("Удален пользователь: " + id);
        } catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Переопределение метода run()
     */
    @Override
    public void run() {
        addUser();
        addUser();
        removeUser();
    }
}
