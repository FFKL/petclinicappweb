package org.petclinic.servlets.pet;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPetServlet extends HttpServlet {
    private final String FORMAT_PATTERN = "%s%s%s";
    private final String REDIRECT_PATH = "/clinic/client?id=";
    private final String JSP_FILE_PATH = "/views/clinic/EditPet.jsp";
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();
    String oldPetName;
    int clientId;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPetName = req.getParameter("pet name");
        if (!newPetName.isEmpty()) {
            try {
                this.CLINIC_CACHE.editPetName(clientId, oldPetName, newPetName);
            } catch (WrongInputException e) {
                e.printStackTrace();
            } catch (IDException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format(REDIRECT_PATH, req.getContextPath(), FORMAT_PATTERN, clientId));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientId = Integer.parseInt(req.getParameter("id"));
        oldPetName = req.getParameter("name");
        forwardTo(req, resp);
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_FILE_PATH);
        dispatcher.forward(req, resp);
    }
}
