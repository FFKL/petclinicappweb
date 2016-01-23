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
    private final String FORMAT_PATTERN = "%s%s%s";
    private final String REDIRECT_PATH = "/clinic/client?id=";
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    int clientId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String petName = req.getParameter("pet name");
        String petType = req.getParameter("type");
        clientId = ClientViewServlet.getId();
        if (!petName.isEmpty()) {
            try {
                this.CLINIC_CACHE.addPet(clientId, petType, petName);
            } catch (IDException e) {
                e.printStackTrace();
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format(FORMAT_PATTERN, req.getContextPath(), REDIRECT_PATH, clientId));
    }
}
