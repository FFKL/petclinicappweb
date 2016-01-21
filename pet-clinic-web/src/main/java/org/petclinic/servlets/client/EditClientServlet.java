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
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();
    private int currentId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        currentId = ClientViewServlet.getId();
        if (!req.getParameter("client name").isEmpty()) {
            try {
                this.CLINIC_CACHE.editClientName(currentId, req.getParameter("client name"));
            } catch (WrongInputException e) {
                e.printStackTrace();
            } catch (IDException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/clinic/client?id=", currentId));
    }
}
