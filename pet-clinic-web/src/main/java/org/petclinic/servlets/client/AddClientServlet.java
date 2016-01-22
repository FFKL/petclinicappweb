package org.petclinic.servlets.client;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddClientServlet extends HttpServlet {

    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    private final String LACK_CLIENT_NAME_MESSAGE = "Введите имя клиента";
    private final String INCORRECT_CLIENT_NAME_MESSAGE = "Ввод имени клиента некорректен. Введите другое имя (Пример: Иванов Иван)";
    private final String INCORRECT_PET_NAME_MESSAGE = "Ввод имени питомца некорректен. Введите другое имя (Пример: Пушок)";
    private final String LACK_ID_MESSAGE = "Такой ID существует! Введите другой";
    private final String INCORRECT_ID_MESSAGE = "Неверно введен ID. Проверьте правильность ввода";
    private final String JSP_FILE_PATH = "/views/clinic/AddClient.jsp";
    private final String REDIRECT_PATH = "/clinic/view";

    private int id;
    private String clientName;
    private String petName;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            id = Integer.parseInt(req.getParameter("id"));
            clientName = req.getParameter("client name");
            petName = req.getParameter("pet name");
            this.CLINIC_CACHE.add(id, clientName);
            if (!req.getParameter("pet name").isEmpty()) {
                this.CLINIC_CACHE.addPet(id, req.getParameter("type"), petName);
            }
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), REDIRECT_PATH));
        } catch (WrongInputException e) {
            wrongInputExceptionTreatment(req);
            forwardTo(req, resp);
        } catch (IDException e) {
            req.setAttribute("message", LACK_ID_MESSAGE);
            forwardTo(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("message", INCORRECT_ID_MESSAGE);
            forwardTo(req, resp);
        }
    }

    private void wrongInputExceptionTreatment(HttpServletRequest req) {
        if (clientName.isEmpty()) {
            req.setAttribute("message", LACK_CLIENT_NAME_MESSAGE);
        } else if (petName.isEmpty()||(!clientName.isEmpty() && !petName.isEmpty())) {
            req.setAttribute("message", INCORRECT_CLIENT_NAME_MESSAGE);
        } else {
            req.setAttribute("message", INCORRECT_PET_NAME_MESSAGE);
        }
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_FILE_PATH);
        dispatcher.forward(req, resp);
    }
}
