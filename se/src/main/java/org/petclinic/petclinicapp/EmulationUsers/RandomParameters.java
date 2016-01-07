package org.petclinic.petclinicapp.EmulationUsers;

import java.util.Random;

/**
 * Параметры для эмуляции действий администратора и пользователя
 */
public class RandomParameters {
    final private Random random = new Random();

    /**
     * Константы, хранящие массивы данных для эмуляции
     */
    final private String[] clientName = new String[] {"Василий", "Юлия", "Дмитрий", "Юрий", "Михаил"};
    final private String[] petName = new String[] {"Рыжик", "Барбос", "Пушок", "Зверек"};
    final private String[] petType = new String[] {"Cat", "Dog"};
    /**
     * Статическая переменная, с помощью которой генерируются ID клиентов в порядке возрастания
     */
    private static int id = 0;

    /**
     * Получение случайного имени клиента из массива
     * @return Имя клиента
     */
    public String getClientName() {
        return randomString(this.clientName);
    }

    /**
     * Получение случайного имени питомца из массива
     * @return Имя питомца
     */
    public String getPetName() {
        return randomString(this.petName);
    }

    /**
     * Получение случайного типа питомца из массива
     * @return Тип питомца
     */
    public String getPetType() {
        return randomString(this.petType);
    }

    /**
     * Получение ID. После каждого вызова метода статическая переменная ID увеличивается на единицу.
     * @return ID + 1;
     */
    public synchronized int getId() {
        notifyAll();
        return ++id;
    }

    /**
     * Получение случайного числа в пределах максимального значения ID
     * @return Случайное число в пределах от 0 до id
     */
    public synchronized int getRandomNum() {
        if (id == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int randomId = id + 1;
        return random.nextInt(randomId);
    }

    /**
     * Метод для работы с массивами строк.
     * @param mas Массив строк
     * @return Случайная строка из массива
     */
    private String randomString(String[] mas) {
        return mas[random.nextInt(mas.length)];
    }
}
