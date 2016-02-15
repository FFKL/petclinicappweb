package org.petclinic.petclinicapp;

import org.petclinic.petclinicapp.Pets.Pet;
import org.petclinic.petclinicapp.Pets.PetCreate;
import org.petclinic.petclinicapp.Pets.PetType;
import org.petclinic.models.Message;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Клиент
 */
public class Client {
    private final String FORMAT_STRING = "%s%s%s%s%s%s";
    /**
     * Переменные, хранящие ID клиента, имя клиента и питомца;
     */
    private int id = 0;
    private String clientName;
    private List<Pet> petList = new ArrayList<Pet>();
    private Role role;
    private Set<Message> messages;

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Конструктор
     */
    public Client(int id, String clientName) {
        this.id = id;
        this.clientName = clientName;
    }

    public Client() {
    }

    public void addPet(String petType, String petName) {
        PetType type = PetType.selectPetType(petType);
        Pet newPet = PetCreate.createPet(type, petName);
        petList.add(newPet);

    }

    public void removePet(String petName) {
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getName().equals(petName)) {
                petList.remove(i);
                break;
            }
        }
    }

    /**
     * Получение питомца
     */
    public List<Pet> getPets(){
        return this.petList;
    }

    /**
     * Получение ID
     */
    public int getId(){
        return this.id;
    }
    /**
     * Получение имени клиента
     */
    public String getClientName(){
        return this.clientName;
    }
    /**
     * Добавление имени клиента
     * @param name имя клиента
     */
    public void setClientName(String name){
        this.clientName = name;
    }

    public void removeAll() {
        this.petList.clear();
    }

    /**
     * Переопределение метода toString()
     * @return строка вида ID клиента + id + Имя клиента + clientName + Имя питомца + petName.
     * В случае отсутствия питомца вместо имени питомца выводит строку "!Питомец отсутствует!"
     */
    @Override
    public String toString() {
        return String.format(FORMAT_STRING, "ID: ", this.id, "; ClientName: ", this.clientName, "; PetList: ", this.petList);
    }

    /**
     * Переопределение метода equals()
     * @param obj объект для сравнения
     * @return результат сравнения двух объектов в boolean виде
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Client e = (Client) obj;
        return (this.getId() == e.getId() && this.getClientName() == e.getClientName() && this.getPets().equals(e.getPets()));
    }

    /**
     * Переопределение метода hashCode();
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        int result = 5;
        result = 31*result + this.clientName.hashCode();
        result = 31*result + this.id;
        result = 31*result + this.getPets().hashCode();
        return result;
    }


    public void setId(int id) {
        this.id = id;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }
}
