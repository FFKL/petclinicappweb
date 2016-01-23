package org.petclinic.servlets.pet;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePetServlet extends HttpServlet {
    private final String FORMAT_PATTERN = "%s%s%s";
    private final String REDIRECT_PATH = "/clinic/client?id=";
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String petName = req.getParameter("name");
        try {
            this.CLINIC_CACHE.delPet(Integer.parseInt(id), petName);
        } catch (WrongInputException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format(FORMAT_PATTERN, req.getContextPath(), REDIRECT_PATH, id));
    }
}
