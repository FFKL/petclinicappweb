package org.petclinic.petclinicapp.Terminal;

import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import java.io.BufferedReader;
import java.io.IOException;

public class TerminalSearch {

    BufferedReader reader;
    Clinic myClinic;
    /**
     * Константа, хранящая основное меню поиска
     */
    private final String SEARCH_MENU = "Поиск. (Введите цифру) Искать по: 1 - имени питомца, 2 - имени клиента";
    /**
     * Константы, хранящие сообщения ввода для вывода в консоль
     */
    private final String ENTER_CLIENT_NAME = "Введите имя клиента";
    private final String ENTER_PET_NAME = "Введите имя питомца";
    private final String ENTER_CORRECT_COMMAND = "Введите корректную команду";
    private final String ENTER_NUM = "Введите цифру";
    /**
     * Константы, хранящие результат операции поиска для вывода в консоль
     */
    private final String SEARCH_RESULT = "Результат поиска: ";
    /**
     * Конструктор
     * @param reader чтение консольного ввода
     * @param myClinic
     * @throws IOException
     */
    public TerminalSearch(BufferedReader reader, Clinic myClinic) throws IOException {
        this.reader = reader;
        this.myClinic = myClinic;
    }
    /**
     * Запуск поиска
     * @throws IOException
     */
    public void start() throws IOException {
        System.out.println(SEARCH_MENU);
        while (true) {
            try {
                int num = Integer.parseInt(reader.readLine());
                if (num == 1) {
                    String petName = io(ENTER_PET_NAME);
                    resultByPetName(myClinic, petName);
                    break;
                } else if (num == 2) {
                    String clientName = io(ENTER_CLIENT_NAME);
                    resultByClientName(myClinic,clientName);
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
     * Результат поиска по имени питомца
     * @param myClinic клиника, в которой производятся изменения
     * @param petName имя питомца для поиска
     */
    private void resultByPetName(Clinic myClinic, String petName) {
        try {
            System.out.println(SEARCH_RESULT + myClinic.findClientsByPetName(petName));
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Результат поиска по имени питомца
     * @param myClinic клиника, в которой производятся изменения
     * @param clientName имя клиента для поиска
     */
    private void resultByClientName(Clinic myClinic, String clientName) {
        try {
            System.out.println(SEARCH_RESULT +  myClinic.findByClientName(clientName));
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
