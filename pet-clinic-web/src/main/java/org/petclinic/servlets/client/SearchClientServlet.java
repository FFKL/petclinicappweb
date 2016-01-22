package org.petclinic.servlets.client;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchClientServlet extends HttpServlet {

    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    private List<Client> searchResult = new ArrayList<Client>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (!req.getParameter("client name").isEmpty()) {
            try {
                searchResult = this.CLINIC_CACHE.searchByClientName(req.getParameter("client name"));
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        } else if (!req.getParameter("pet name").isEmpty() && !searchResult.isEmpty()){
            searchResult.clear();
        }
        if (!req.getParameter("pet name").isEmpty()) {
            try {
                List<Client> petSearchList = this.CLINIC_CACHE.searchByPetName(req.getParameter("pet name"));
                if (searchResult.isEmpty()) {
                    searchResult = petSearchList;
                }
                else
                    searchResult.addAll(petSearchList);
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