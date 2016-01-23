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
    private final String JSP_FILE_PATH = "/views/clinic/SearchClient.jsp";
    private final String REDIRECT_PATH = "/clinic/search";
    private final String FORMAT_PATTERN = "%s%s";
    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    private List<Client> searchResult = new ArrayList<Client>();
    private String clientName;
    private String petName;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        clientName = req.getParameter("client name");
        petName = req.getParameter("pet name");
        if (!clientName.isEmpty()) {
            searchByClientName();
        } else if (!petName.isEmpty() && !searchResult.isEmpty()){
            searchResult.clear();
        }
        if (!petName.isEmpty()) {
            searchByPetName();
        }
        resp.sendRedirect(String.format(FORMAT_PATTERN, req.getContextPath(), REDIRECT_PATH));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", searchResult);
        forwardTo(req, resp);
    }

    public List<Client> getSearchResult() {
        return searchResult;
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_FILE_PATH);
        dispatcher.forward(req, resp);
    }

    private void searchByClientName() {
        try {
            searchResult = this.CLINIC_CACHE.searchByClientName(clientName);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
    }

    private void searchByPetName() {
        try {
            List<Client> petSearchList = this.CLINIC_CACHE.searchByPetName(petName);
            if (searchResult.isEmpty()) {
                searchResult = petSearchList;
            }
            else
                searchResult.addAll(petSearchList);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
    }
}