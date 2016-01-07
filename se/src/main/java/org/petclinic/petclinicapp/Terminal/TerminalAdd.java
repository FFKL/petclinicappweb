package org.petclinic.petclinicapp.Terminal;

import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;

import java.io.BufferedReader;
import java.io.IOException;



public class TerminalAdd {

    BufferedReader reader;
    Clinic myClinic;
    /**
     * Константы, хранящие сообщения для вывода в консоль
     */
    private final String ENTER_ID = "Введите ID клиента";
    private final String ENTER_CLIENT_NAME = "Введите имя клиента";
    private final String ENTER_PET_NAME = "Введите имя питомца";
    private final String ENTER_PET_TYPE = "Введите тип питомца: Cat/Dog";
    private final String CLIENT_ADDED = "Клиент добавлен!";
    /**
     * Переменные, хранящие атрибуты добавляемого объекта
     */
    int id;
    String clientName;
    String petType;
    String petName;

    /**
     * Конструктор
     * @param reader чтение консольного ввода
     * @param myClinic объект клиники
     * @throws IOException
     */
    public TerminalAdd(BufferedReader reader, Clinic myClinic) throws IOException {
        this.reader = reader;
        this.myClinic = myClinic;
    }

    /**
     * Запуск добавления
     * @throws IOException
     */
    public void start() throws IOException {
        this.id = Integer.parseInt(io(ENTER_ID));
        this.clientName = io(ENTER_CLIENT_NAME);
        this.petType = io(ENTER_PET_TYPE);
        this.petName = io(ENTER_PET_NAME);
        endOfAdd(myClinic);
    }
    /**
     * Ввод-вывод
     * @param enter строка, отображающаяся в консоли
     * @return ввод консоли
     * @throws IOException
     */
    private String io(String enter) throws IOException {
        System.out.println(enter);
        return reader.readLine();
    }

    /**
     * Результат добавления и вывод его в консоль
     * @param myClinic клиника, в которую поизводится добавление
     */
    private void endOfAdd(Clinic myClinic) {
        try {
            myClinic.addClient(this.id, this.clientName, this.petType, this.petName);
            System.out.println(CLIENT_ADDED);
        } catch (IDException e) {
            System.out.println(e.getMessage());
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        } catch (PetTypeException e) {
            System.out.println(e.getMessage());
        }
    }
}
