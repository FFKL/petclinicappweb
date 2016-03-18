package org.petclinic.servlets;

import org.petclinic.store.ClinicCache;
import org.petclinic.store.Hibernate.ClientStorage;
import org.petclinic.store.Hibernate.Storages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClinicViewServlet extends HttpServlet {

    /*private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();*/
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    Storages storage = context.getBean(Storages.class);

    private final String JSP_FILE_PATH = "/views/clinic/ClinicView.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", this.storage.clientStorage.values());
        forwardTo(req, resp);
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_FILE_PATH);
        dispatcher.forward(req, resp);
    }
}
