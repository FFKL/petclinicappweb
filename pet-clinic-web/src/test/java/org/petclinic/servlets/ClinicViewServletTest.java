package org.petclinic.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class ClinicViewServletTest extends Mockito {

    private final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Test
    public void testClinicView() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/views/clinic/ClinicView.jsp")).thenReturn(dispatcher);

        ClinicViewServlet view = new ClinicViewServlet();
        view.doGet(req, resp);

        assertTrue(CLINIC_CACHE.isEmpty());
    }
}