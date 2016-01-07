package org.petclinic.petclinicapp.Terminal;

import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;

import java.io.BufferedReader;
import java.io.IOException;

public class TerminalDel {

    BufferedReader reader;
    Clinic myClinic;
    /**
     * Константа, хранящая основное меню удаления
     */
    private final String DEL_MENU = "Удаление. (Введите цифру) Удалить: 1 - питомца, 2 - клиента";
    /**
     * Константы, хранящие сообщения ввода для вывода в консоль
     */
    private final String ENTER_ID = "Введите ID клиента";
    private final String ENTER_CORRECT_COMMAND = "Введите корректную команду";
    private final String ENTER_NUM = "Введите цифру";
    /**
     * Константы, хранящие результат операции удаления для вывода в консоль
     */
    private final String PET_DEL = "Питомец удален";
    private final String CLIENT_DEL = "Клиент удален";

    /**
     * Конструктор
     * @param reader чтение консольного ввода
     * @param myClinic объект клиники
     * @throws IOException
     */
    public TerminalDel(BufferedReader reader, Clinic myClinic) throws IOException {
        this.reader = reader;
        this.myClinic = myClinic;
    }

    /**
     * запуск удаления
     * @throws IOException
     */
    public void start() throws IOException {
        try {
            System.out.println(DEL_MENU);
            while (true) {
                int numSearch = Integer.parseInt(reader.readLine());
                if (numSearch == 1) {
                    try {
                        myClinic.removePet(Integer.parseInt(io(ENTER_ID)));
                        System.out.println(PET_DEL);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                } else if (numSearch == 2) {
                    try {
                        myClinic.removeClient(Integer.parseInt(io(ENTER_ID)));
                        System.out.println(CLIENT_DEL);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                } else {
                    System.out.println(ENTER_CORRECT_COMMAND);
                }
            }
        }
        catch (NumberFormatException e) {
            System.out.println(ENTER_NUM);
        }
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
}
