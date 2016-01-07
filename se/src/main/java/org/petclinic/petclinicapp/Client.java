package org.petclinic.petclinicapp;

import org.petclinic.petclinicapp.Pets.Pet;

/**
 * Клиент
 */
public class Client {
    /**
     * Переменные, хранящие ID клиента, имя клиента и питомца;
     */
    private int id = 0;
    private String clientName;
    private Pet pet;
    /**
     * Конструктор
     */
    public Client(int id, String clientName, Pet pet) {
        this.id = id;
        this.clientName = clientName;
        this.pet = pet;
    }

    /**
     * Получение питомца
     */
    public Pet getPet(){
        return this.pet;
    }
    /**
     * Удаление питомца
     */
    public void removePet() {
        this.pet = null;
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

    /**
     * Переопределение метода toString()
     * @return строка вида ID клиента + id + Имя клиента + clientName + Имя питомца + petName.
     * В случае отсутствия питомца вместо имени питомца выводит строку "!Питомец отсутствует!"
     */
    @Override
    public String toString() {
        String petName;
        if (this.pet == null)
            petName = "!Питомец отсутствует!";
        else
            petName = "; Имя питомца: " +  this.pet.getName();
        return "ID клиента: " + this.id + "; Имя клиента: " + this.clientName  + petName;
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
        return (this.getId() == e.getId() && this.getClientName() == e.getClientName() && this.getPet().equals(e.getPet()));
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
        result = 31*result + this.getPet().hashCode();
        return result;
    }
}
