package org.petclinic.servlets;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClinicServletTest extends Mockito {

    @Test
    public void testClinicServlet() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Fedor");
        when(req.getParameter("type")).thenReturn("Cat");
        when(req.getParameter("pet")).thenReturn("Pushok");
        when(req.getParameter("search name")).thenReturn("Pushok");
        when(resp.getWriter()).thenReturn(writer);

        ClinicServlet servlet = new ClinicServlet();
        assertTrue(servlet.clinic.getClients().isEmpty());
        servlet.doPost(req, resp);

        verify(req, atLeast(1)).getParameter("id");
        verify(req, atLeast(1)).getParameter("name");
        verify(req, atLeast(1)).getParameter("type");
        verify(req, atLeast(1)).getParameter("pet");
        verify(req, atLeast(1)).getParameter("search name");

        assertFalse(servlet.clinic.getClients().isEmpty());

        servlet.clinic.removeAll();
    }
}