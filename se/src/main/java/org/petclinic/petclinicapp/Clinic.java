package org.petclinic.petclinicapp;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
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
    private final String NAME_REGEXP = "^[a-zA-Zа-яА-Я'][a-zA-Zа-яА-Я-' ]+[a-zA-Zа-яА-Я']?$";
    /**
     * Константы для сообщений в исключениях
     */
    private final String ID_EXCEPTION_MESSAGE =  "Введенный ID существует. Введите другой.";
    private final String ID_EXCEPTION_MESSAGE_SEARCH = "Клиента с введенным ID не существует";
    private final String WRONG_INPUT_EXCEPTION_MESSAGE = "Ввод имени некорректен. Введите другое имя (Пример: Василий)";
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
     * @throws WrongInputException, если имя клиента или имя питомца содержат цифры
     * @throws IDException, если существует клиент с введенным ID
     */
    public void addClient(final int id, String clientName) throws WrongInputException, IDException {
        for (Client c : this.clients) {
            if (id == c.getId())
                throw new IDException(ID_EXCEPTION_MESSAGE);
        }
        checkCorrectInput(clientName);
        clients.add(new Client(id, clientName));
    }

    public void addPetForClient(int id, String petType, String petName) throws IDException, WrongInputException {
        Client client = this.findById(id);
        checkCorrectInput(petType);
        checkCorrectInput(petName);
        client.addPet(petType, petName);
    }

    /**
     * Нахождение клиента по имени питомца
     * @param petName имя клиента
     * @return List клиентов с заданным именем
     * @throws WrongInputException, если имя питомца содержит цифры
     */
    public List<Client> findClientsByPetName(final String petName) throws WrongInputException {
        checkCorrectInput(petName);
        List<Client> clientsWithCurrentPet = new ArrayList<Client>();
        for (Client c : clients) {
            List<Pet> pets = c.getPets();
            for (Pet p : pets) {
                if (p.getName().equals(petName))
                    clientsWithCurrentPet.add(c);
            }
        }
        return clientsWithCurrentPet;
    }
    /**
     * Нахождение клиента по собственному имени
     * @param clientName имя клиента
     * @return List клиентов с заданным именем
     * @throws WrongInputException, если имя клиента содержит цифры
     */
    public List<Client> findByClientName(final String clientName) throws WrongInputException {
        checkCorrectInput(clientName);
        List<Client> clientsWithCurrentName = new ArrayList<Client>();
        for (Client c : clients) {
            if (c.getClientName().equals(clientName))
                clientsWithCurrentName.add(c);
        }
        return clientsWithCurrentName;
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
        boolean comparisonId = false;
        checkCorrectInput(clientName);
            for (Client c : clients) {
                if (c.getId() == id){
                    comparisonId = true;
                    c.setClientName(clientName);
                    break;
                }
            }
        if (!comparisonId)
            throw new IDException(ID_EXCEPTION_MESSAGE);
    }
    /**
     * Изменение имени питомца
     * @param id ID клиента
     * @param currentPetName текущее имя питомца
     * @throws WrongInputException, если имя питомца содержит цифры
     * @throws IDException, если существует клиент с введенным ID
     */
    public void changePetName(int id, String currentPetName, String newPetName) throws WrongInputException, IDException {
        boolean comparisonId = false;
        checkCorrectInput(currentPetName);
        checkCorrectInput(newPetName);
            for (Client c : clients) {
                if (c.getId() == id) {
                    comparisonId = true;
                    List<Pet> pets = c.getPets();
                    for (Pet p : pets) {
                        if (p.getName().equals(currentPetName))
                            p.setName(newPetName);
                    }
                    break;
                }
            }
        if (!comparisonId)
            throw new IDException(ID_EXCEPTION_MESSAGE);
    }

    public void removePet(int id, String petName) throws WrongInputException, IDException {
        boolean comparisonId = false;
        checkCorrectInput(petName);
        for (Client c : clients) {
            if (c.getId() == id) {
                comparisonId = true;
                c.removePet(petName);
                break;
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
    public void removeClient(int id) throws IDException {
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
     * Получение листа клиентов
     * @return List клиентов
     */
    public List<Client> getClients() {
        return clients;
    }

    public boolean isEmpty() {
        return clients.isEmpty();
    }

    public void removeAll() {
        clients.clear();
    }

    private void checkCorrectInput(String name) throws WrongInputException {
        if (!name.matches(NAME_REGEXP)) {
            throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
        }
    }
}
