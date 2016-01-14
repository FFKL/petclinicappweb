package org.petclinic.servlets;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchClientServlet extends HttpServlet {

    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    private List<Client> searchResult;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("owner").isEmpty()) {
            try {
                searchResult = this.CLINIC_CACHE.searchByClientName(req.getParameter("owner"));
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }
        if (!req.getParameter("pet").isEmpty()) {
            try {
                List<Client> petList = this.CLINIC_CACHE.searchByPetName(req.getParameter("pet"));
                if (searchResult.isEmpty()) {
                    searchResult = petList;
                }
                else
                    searchResult.addAll(petList);
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/search"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", searchResult);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/SearchClient.jsp");
        dispatcher.forward(req, resp);
    }
}