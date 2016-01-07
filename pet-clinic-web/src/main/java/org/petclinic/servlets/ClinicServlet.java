package org.petclinic.servlets;

import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Clinic;
import org.petclinic.petclinicapp.Exceptions.IDException;
import org.petclinic.petclinicapp.Exceptions.PetTypeException;
import org.petclinic.petclinicapp.Exceptions.WrongInputException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClinicServlet extends HttpServlet {
    final Clinic clinic = new Clinic();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Owner name : <input type='text' name='name'>"+
                        "         Pet name : <input type='text' name='pet'>"+
                        "         Pet type (Cat/Dog) : <input type='text' name='type'>"+
                        "         ClientId : <input type='text' name='id'>"+
                        "         <input type='submit' value='Submit'>"+
                        "     <form>"+
                        this.viewPets() +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.clinic.addClient(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("type"), req.getParameter("pet"));
        } catch (WrongInputException e) {
            e.printStackTrace();
        } catch (IDException e) {
            e.printStackTrace();
        } catch (PetTypeException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }

    private String viewPets() {
        List<Client> clients = clinic.getClients();
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets</p>");
        sb.append("<table style='border : 1px solid black'>");
        sb.append("<tr><td style='border : 1px solid black'>ID</td>" +
                "<td style='border : 1px solid black'>CLIENT_NAME</td>" +
                "<td style='border : 1px solid black'>PET_TYPE</td>" +
                "<td style='border : 1px solid black'>PET_NAME</td></tr>");
        for (Client client : clients) {
            sb.append("<tr><td style='border : 1px solid black'>").append(client.getId()).append("</td>" +
                    "<td style='border : 1px solid black'>").append(client.getClientName()).append("</td>" +
                    "<td style='border : 1px solid black'>").append(client.getPet().makeSound()).append("</td>" +
                    "<td style='border : 1px solid black'>").append(client.getPet().getName()).append("</td></tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}