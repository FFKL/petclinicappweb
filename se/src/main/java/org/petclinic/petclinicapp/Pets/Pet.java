package org.petclinic.petclinicapp.Pets;
/**
 * Питомец
 */
public interface Pet {
    /**
     * Получение имени питомца
     * @return Имя питомца
     */
    public String getName();

    /**
     * Задание имени питомца
     * @param name Имя питомца
     */
    public void setName(String name);

    /**
     * Питомец подает голос
     * @return Голос питомца в виде строки
     */
    public String makeSound();

}
