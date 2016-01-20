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

public class EditClientServlet extends HttpServlet {
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();
    private int currentId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("client name").isEmpty()) {
            try {
                this.CLINIC_CACHE.editClientName(currentId, req.getParameter("client name"));
            } catch (WrongInputException e) {
                e.printStackTrace();
            } catch (IDException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            currentId = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("client", this.CLINIC_CACHE.searchById(currentId));
            req.setAttribute("pets", this.CLINIC_CACHE.searchById(currentId).getPets());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/EditClient.jsp");
            dispatcher.forward(req, resp);
        } catch (IDException e) {
            e.printStackTrace();
        }
    }
}
