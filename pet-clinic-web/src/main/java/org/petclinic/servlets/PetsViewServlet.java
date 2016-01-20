package org.petclinic.servlets;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetsViewServlet extends HttpServlet {
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("pets", this.CLINIC_CACHE.searchById(Integer.parseInt(req.getParameter("id"))).getPets());
            req.setAttribute("id", req.getParameter("id"));
        } catch (IDException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/PetsView.jsp");
        dispatcher.forward(req, resp);
    }
}
