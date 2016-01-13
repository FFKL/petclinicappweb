package org.petclinic.petclinicapp;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.petclinicapp.Pets.Dog;
import org.petclinic.petclinicapp.Pets.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает клинику.
 */
public class Clinic {

    private final List<Client> clients;
    /**
     * Регулярное выражение
     */
    final String CONTAINS_NO_NUMBERS_REGEXP = "\\D+";
    /**
     * Константы для сообщений в исключениях
     */
    final String ID_EXCEPTION_MESSAGE =  "Введенный ID существует. Введите другой.";
    final String ID_EXCEPTION_MESSAGE_SEARCH = "Клиента с введенным ID не существует";
    final String WRONG_INPUT_EXCEPTION_MESSAGE = "Ввод имени содержит цифры. Введите корректное имя (Пример: Василий)";
    final String PET_TYPE_EXCEPTION_MESSAGE = "Такого питомца не существует.";
    /**
     * Конструктор
     */
    public Clinic() {
        this.clients = new ArrayList<Client>();
    }
    /**
     * Добавление клиента
     * @param id ID клиента
     * @param clientName Имя клиента
     * @param petType Тип питомца
     * @param petName Имя питомца
     * @throws WrongInputException, если имя клиента или имя питомца содержат цифры
     * @throws IDException, если существует клиент с введенным ID
     * @throws PetTypeException, если введет несуществующий тип питомца
     */
    public synchronized void addClient(final int id, String clientName, String petType, String petName) throws WrongInputException, IDException, PetTypeException {
        Pet pet = null;
        for (Client c : this.clients) {
            if (id == c.getId())
                throw new IDException(ID_EXCEPTION_MESSAGE);
        }
        if (!clientName.matches(CONTAINS_NO_NUMBERS_REGEXP) || !petName.matches(CONTAINS_NO_NUMBERS_REGEXP)) {
            throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
        }
        else if (!petType.equals("Cat") && !petType.equals("Dog")) {
            throw new PetTypeException(PET_TYPE_EXCEPTION_MESSAGE);
        }
        else {
            if (petType.equals("Dog"))
                pet = new Dog(petName);
            else if (petType.equals("Cat"))
                pet = new Cat(petName);
            this.clients.add(new Client(id, clientName, pet));
        }
    }

    /**
     * Нахождение клиента по имени питомца
     * @param petName имя клиента
     * @return List клиентов с заданным именем
     * @throws WrongInputException, если имя питомца содержит цифры
     */
    public List<Client> findClientsByPetName(final String petName) throws WrongInputException {
        if (!petName.matches(CONTAINS_NO_NUMBERS_REGEXP)) {
            throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
        }
        else {
                List<Client> clientsWithCurrentPet = new ArrayList<Client>();
                for (Client c : clients) {
                    String name = c.getPet().getName();
                    if (name.equals(petName))
                        clientsWithCurrentPet.add(c);
                }
            return clientsWithCurrentPet;
        }
    }
    /**
     * Нахождение клиента по собственному имени
     * @param clientName имя клиента
     * @return List клиентов с заданным именем
     * @throws WrongInputException, если имя клиента содержит цифры
     */
    public List<Client> findByClientName(final String clientName) throws WrongInputException {
        if (!clientName.matches(CONTAINS_NO_NUMBERS_REGEXP)) {
            throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
        }
        else {
            List<Client> clientsWithCurrentName = new ArrayList<Client>();
            for (Client c : clients) {
                if (c.getClientName().equals(clientName))
                    clientsWithCurrentName.add(c);
            }
            return clientsWithCurrentName;
        }
    }

    public Client findById(final int id) throws IDException {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new IDException(ID_EXCEPTION_MESSAGE_SEARCH);
    }

    /**
     * Изменение имени клиента
     * @param id ID клиента
     * @param clientName имя клиента
     * @throws WrongInputException, если имя клиента содержит цифры
     * @throws IDException, если существует клиент с введенным ID
     */
    public void changeClientName(int id, String clientName) throws WrongInputException, IDException {
        if (!clientName.matches(CONTAINS_NO_NUMBERS_REGEXP)) {
            throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
        }
        else {
            for (Client c : clients) {
                if (c.getId() == id){
                    c.setClientName(clientName);
                    break;
                } else
                    throw new IDException(ID_EXCEPTION_MESSAGE);
            }
        }
    }
    /**
     * Изменение имени питомца
     * @param id ID клиента
     * @param petName имя питомца
     * @throws WrongInputException, если имя питомца содержит цифры
     * @throws IDException, если существует клиент с введенным ID
     */
    public void changePetName(int id, String petName) throws WrongInputException, IDException {
        boolean comparisonId = false;
        if (!petName.matches(CONTAINS_NO_NUMBERS_REGEXP)) {
            throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
        }
        else {
            for (Client c : clients) {
                if (c.getId() == id) {
                    comparisonId = true;
                    c.getPet().setName(petName);
                    break;
                }
            }
        }
        if (!comparisonId)
            throw new IDException(ID_EXCEPTION_MESSAGE);
    }
    /**
     * Удаление клиента
     * @param id ID клиента
     * @throws IDException, если существует клиент с введенным ID
     */
    public synchronized void removeClient(int id) throws IDException {
        if (clients == null)  {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean comparisonId = false;
        for (int i = 0; i < clients.size(); i++){
            if (clients.get(i).getId() == id){
                comparisonId = true;
                clients.remove(i);
                break;
            }
        }
        if (!comparisonId)
            throw new IDException(ID_EXCEPTION_MESSAGE);
    }
    /**
     * Удаление питомца
     * @param id ID килента
     * @throws IDException, если существует клиент с введенным ID
     */
    public void removePet(int id) throws IDException {
        for (int i = 0; i < clients.size(); i++){
            if (clients.get(i).getId() == id){
                clients.get(i).removePet();
                break;
            } else {
                throw new IDException(ID_EXCEPTION_MESSAGE);
            }
        }
    }

    /**
     * Вывод списка клиентов клиники на экран
     * В случае отсутствия питомца вместо имени питомца выводит строку "!Питомец отсутствует!"
     */
    public void clientList() {
        for (Client c : clients) {
            String petName;
            if (c.getPet() == null)
                petName = "!Питомец отсутствует!";
            else
                petName = "; Имя питомца: " +  c.getPet().getName();
            System.out.println("ID клиента: " + c.getId() + "; Имя клиента: " + c.getClientName() + petName);
        }
    }

    /**
     * Получение листа клиентов
     * @return List клиентов
     */
    public List<Client> getClients() {
        return clients;
    }

    public boolean isEmpty() {
        if (clients == null)
            return true;
        return false;
    }
}
