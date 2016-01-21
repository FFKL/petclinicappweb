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

public class DeletePetServlet extends HttpServlet {
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.CLINIC_CACHE.delPet(Integer.parseInt(req.getParameter("id")), req.getParameter("name"));
        } catch (WrongInputException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/clinic/client?id=", req.getParameter("id")));
    }
}
