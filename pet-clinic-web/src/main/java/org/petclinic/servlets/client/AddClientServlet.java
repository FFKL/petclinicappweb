package org.petclinic.servlets.client;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddClientServlet extends HttpServlet {

    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(req.getParameter("id"));
            this.CLINIC_CACHE.add(id, req.getParameter("client name"));
            if (!req.getParameter("pet name").isEmpty()) {
                this.CLINIC_CACHE.addPet(id, req.getParameter("type"), req.getParameter("pet name"));
            }
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
        } catch (WrongInputException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        }
    }
}
