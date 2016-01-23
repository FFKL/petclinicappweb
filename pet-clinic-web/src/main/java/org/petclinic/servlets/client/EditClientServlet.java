package org.petclinic.servlets.client;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditClientServlet extends HttpServlet {
    private final String FORMAT_PATTERN = "%s%s%s";
    private final String REDIRECT_PATH = "/clinic/client?id=";
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentId = ClientViewServlet.getId();
        String clientName = req.getParameter("client name");
        if (!clientName.isEmpty()) {
            try {
                this.CLINIC_CACHE.editClientName(currentId, clientName);
            } catch (WrongInputException e) {
                e.printStackTrace();
            } catch (IDException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format(FORMAT_PATTERN, req.getContextPath(), REDIRECT_PATH, currentId));
    }
}
