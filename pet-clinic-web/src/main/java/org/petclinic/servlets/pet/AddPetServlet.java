package org.petclinic.servlets.pet;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.servlets.client.ClientViewServlet;
import org.petclinic.store.ClinicCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPetServlet extends HttpServlet {
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();
    int clientId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientId = ClientViewServlet.getId();
        if (!req.getParameter("pet name").isEmpty()) {
            try {
                this.CLINIC_CACHE.addPet(clientId, req.getParameter("type"), req.getParameter("pet name"));
            } catch (IDException e) {
                e.printStackTrace();
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/clinic/client?id=", clientId));
    }
}
