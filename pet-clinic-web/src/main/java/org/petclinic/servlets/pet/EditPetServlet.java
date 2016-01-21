package org.petclinic.servlets.pet;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.servlets.client.ClientViewServlet;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPetServlet extends HttpServlet {
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();
    String petName;
    int clientId;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientId = ClientViewServlet.getId();
        if (!req.getParameter("pet name").isEmpty()) {
            try {
                this.CLINIC_CACHE.editPetName(clientId, petName, req.getParameter("pet name"));
            } catch (WrongInputException e) {
                e.printStackTrace();
            } catch (IDException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/clinic/client?id=", clientId));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        petName = req.getParameter("name");
        clientId = Integer.parseInt(req.getParameter("id"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/EditPet.jsp");
        dispatcher.forward(req, resp);
    }
}
