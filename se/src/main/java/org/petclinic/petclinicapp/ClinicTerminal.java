package org.petclinic.petclinicapp;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.petclinicapp.Terminal.TerminalAdd;
import org.petclinic.petclinicapp.Terminal.TerminalDel;
import org.petclinic.petclinicapp.Terminal.TerminalEdit;
import org.petclinic.petclinicapp.Terminal.TerminalSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClinicTerminal {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Clinic myClinic = new Clinic();
    /**
     * Константа, хранящая основное меню для вывода в консоль
     */
    final String MENU = "Вас приветствует Клиника для животных! Выберите действие 1 - Добавление; 2 - Поиск; 3 - Редактирование; 4 - Удаление; 5 - Показать всех клиентов";
    /**
     * Константы, хранящие сообщения об ошибках для вывода в консоль
     */
    private final String ENTER_CORRECT_COMMAND = "Введите корректную команду";
    private final String ENTER_NUM = "Введите цифру";
    /**
     * Переменные для объектов добавления, поиска, изменения и удаления
     */
    TerminalAdd add;
    TerminalSearch search;
    TerminalEdit edit;
    TerminalDel del;

    /**
     * Конструктор
     * @throws IOException
     */
    public ClinicTerminal() throws IOException {
        this.add = new TerminalAdd(reader, myClinic);
        this.search = new TerminalSearch(reader, myClinic);
        this.edit = new TerminalEdit(reader, myClinic);
        this.del = new TerminalDel(reader, myClinic);
    }

    /**
     * Основное меню
     * @throws IOException
     */
    public void mainMenu() throws IOException {
        while (true) {
            System.out.println(MENU);
            try {
                int action = Integer.parseInt(reader.readLine());
                switch (action) {
                    case 1:
                        add.start();
                        break;
                    case 2:
                        search.start();
                        break;
                    case 3:
                        edit.start();
                        break;
                    case 4:
                        del.start();
                        break;
                    case 5:
                        myClinic.clientList();
                        break;
                    default:
                        System.out.println(ENTER_CORRECT_COMMAND);
                        break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println(ENTER_NUM);
            }
        }

    }
}
