package org.petclinic.petclinicapp.Terminal;

import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import java.io.BufferedReader;
import java.io.IOException;

public class TerminalEdit {

    BufferedReader reader;
    Clinic myClinic;
    /**
     * Константа, хранящая основное меню изменения
     */
    private final String EDIT_MENU = "Редактирование. (Введите цифру) Редактировать: 1 - имя питомца, 2 - имя клиента";
    /**
     * Константы, хранящие сообщения ввода для вывода в консоль
     */
    private final String ENTER_ID = "Введите ID клиента";
    private final String ENTER_CORRECT_COMMAND = "Введите корректную команду";
    private final String ENTER_NUM = "Введите цифру";
    private final String ENTER_NEW_PET_NAME = "Введите новое имя питомца";
    private final String ENTER_NEW_CLIENT_NAME = "Введите новое имя клиента";
    /**
     * Константы, хранящие результат операции изменения для вывода в консоль
     */
    private final String PET_NAME_CHANGED = "Имя питомца изменено";
    private final String CLIENT_NAME_CHANGED = "Имя клиента изменено";

    /**
     * Конструктор
     * @param reader чтение консольного ввода
     * @param myClinic
     * @throws IOException
     */
    public TerminalEdit(BufferedReader reader, Clinic myClinic) throws IOException {
        this.reader = reader;
        this.myClinic = myClinic;
    }

    /**
     * Запуск изменения
     * @throws IOException
     */
    public void start() throws IOException {
        System.out.println(EDIT_MENU);
        while (true) {
            try {
                int numSearch = Integer.parseInt(reader.readLine());
                if (numSearch == 1) {
                    int idSearch = Integer.parseInt(io(ENTER_ID));
                    String petName = io(ENTER_NEW_PET_NAME);
                    resultByPetName(myClinic, idSearch, petName);
                    break;
                } else if (numSearch == 2) {
                    int idSearch = Integer.parseInt(io(ENTER_ID));
                    String clientName = io(ENTER_NEW_CLIENT_NAME);
                    resultByClientName(myClinic, idSearch, clientName);
                    break;
                } else {
                    System.out.println(ENTER_CORRECT_COMMAND);
                }
            }
            catch (NumberFormatException e) {
                System.out.println(ENTER_NUM);
            }
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

    /**
     * Результат изменения имени питомца
     * @param myClinic клиника, в которой производятся изменения
     * @param idSearch id клиента
     * @param petName новое имя питомца
     */
    private void resultByPetName(Clinic myClinic, int idSearch, String petName) {
        try {
            myClinic.changePetName(idSearch, petName);
            System.out.println(PET_NAME_CHANGED);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        } catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Результат изменения имени питомца
     * @param myClinic клиника, в которой производятся изменения
     * @param idSearch id клиента
     * @param clientName новое имя клиента
     */
    private void resultByClientName(Clinic myClinic, int idSearch, String clientName) {
        try {
            myClinic.changeClientName(idSearch, clientName);
            System.out.println(CLIENT_NAME_CHANGED);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        } catch (IDException e) {
            System.out.println(e.getMessage());
        }
    }
}
