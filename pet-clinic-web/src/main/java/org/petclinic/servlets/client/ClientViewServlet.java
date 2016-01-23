package org.petclinic.servlets.client;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientViewServlet extends HttpServlet {

    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();
    private final String JSP_FILE_PATH = "/views/clinic/ClientView.jsp";
    private static int id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("pets", this.CLINIC_CACHE.searchById(id).getPets());
            req.setAttribute("client", this.CLINIC_CACHE.searchById(id));
        } catch (IDException e) {
            //nothing
        }
        forwardTo(req, resp);
    }

    public static int getId() {
        return id;
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_FILE_PATH);
        dispatcher.forward(req, resp);
    }
}
