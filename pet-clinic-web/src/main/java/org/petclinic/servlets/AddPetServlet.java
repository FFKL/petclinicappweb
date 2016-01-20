package org.petclinic.servlets;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
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
        if (!req.getParameter("pet name").isEmpty()) {
            try {
                this.CLINIC_CACHE.addPet(clientId, req.getParameter("type"), req.getParameter("pet name"));
            } catch (IDException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/clinic/addpet?id=", clientId));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientId = Integer.parseInt(req.getParameter("id"));
        try {
            req.setAttribute("pets", this.CLINIC_CACHE.searchById(clientId).getPets());
        } catch (IDException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/AddPet.jsp");
        dispatcher.forward(req, resp);
    }
}
