package org.petclinic.servlets.client;

import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.store.ClinicCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteClientServlet extends HttpServlet {
    private final String FORMAT_PATTERN = "%s%s";
    private final String REDIRECT_PATH = "/clinic/view";
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.CLINIC_CACHE.delClient(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(String.format(FORMAT_PATTERN, req.getContextPath(), REDIRECT_PATH));
        } catch (IDException e) {
            e.printStackTrace();
        }
    }
}
