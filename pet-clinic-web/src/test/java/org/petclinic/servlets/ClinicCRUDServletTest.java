package org.petclinic.servlets;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ClinicCRUDServletTest extends Mockito {

    final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Test
    public void testAddUser() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("owner")).thenReturn("Fedor");
        when(req.getParameter("type")).thenReturn("Cat");
        when(req.getParameter("pet")).thenReturn("Pushok");

        assertTrue(CLINIC_CACHE.getClients().isEmpty());

        new AddClientServlet().doPost(req, resp);

        verify(req, atLeast(1)).getParameter("id");
        verify(req, atLeast(1)).getParameter("owner");
        verify(req, atLeast(1)).getParameter("type");
        verify(req, atLeast(1)).getParameter("pet");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        CLINIC_CACHE.delClient(CLINIC_CACHE.searchByClientName("Fedor").get(0).getId());
    }

    @Test
    public void testDelUser() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        CLINIC_CACHE.add(3, "Fedor", "Cat", "Pushok");
        when(req.getParameter("id")).thenReturn("3");
        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        new DeleteClientServlet().doGet(req, resp);
        verify(req, atLeast(1)).getParameter("id");
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testEditUserName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        CLINIC_CACHE.add(2, "Fedor", "Cat", "Pushok");
        CLINIC_CACHE.add(3, "Fedor", "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("id")).thenReturn("3");
        when(req.getParameter("owner")).thenReturn("Grigoriy");
        when(req.getParameter("pet")).thenReturn("");
        when(req.getRequestDispatcher("/views/clinic/EditClient.jsp")).thenReturn(requestDispatcher);

        EditClientServlet edit =  new EditClientServlet();

        edit.doGet(req, resp);
        verify(req, atLeast(1)).getParameter("id");

        edit.doPost(req, resp);
        verify(req, atLeast(1)).getParameter("owner");
        verify(req, atLeast(1)).getParameter("pet");

        Client c = new Client(3, "Grigoriy", new Cat("Pushok"));
        assertEquals(c, CLINIC_CACHE.getClients().get(1));

        CLINIC_CACHE.removeAll();

        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testEditPetName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        CLINIC_CACHE.add(2, "Fedor", "Cat", "Pushok");
        CLINIC_CACHE.add(3, "Fedor", "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("id")).thenReturn("3");
        when(req.getParameter("owner")).thenReturn("");
        when(req.getParameter("pet")).thenReturn("Snezhok");
        when(req.getRequestDispatcher("/views/clinic/EditClient.jsp")).thenReturn(requestDispatcher);

        EditClientServlet edit =  new EditClientServlet();

        edit.doGet(req, resp);
        verify(req, atLeast(1)).getParameter("id");

        edit.doPost(req, resp);
        verify(req, atLeast(1)).getParameter("owner");
        verify(req, atLeast(1)).getParameter("pet");

        Client c = new Client(3, "Fedor", new Cat("Snezhok"));
        assertEquals(c, CLINIC_CACHE.getClients().get(1));

        CLINIC_CACHE.removeAll();
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testSearchByClientName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(1, "Mariya", new Cat("Snezhok")));
        clients.add(new Client(3, "Mariya", new Cat("Pushok")));

        CLINIC_CACHE.add(1, "Mariya", "Cat", "Snezhok");
        CLINIC_CACHE.add(2, "Igor", "Dog", "Killer");
        CLINIC_CACHE.add(3, "Mariya", "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("owner")).thenReturn("Mariya");
        when(req.getParameter("pet")).thenReturn("");

        SearchClientServlet search = new SearchClientServlet();
        search.doPost(req, resp);
        verify(req, atLeast(1)).getParameter("owner");
        verify(req, atLeast(1)).getParameter("pet");

        assertEquals(search.getSearchResult(), clients);

        CLINIC_CACHE.removeAll();
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testSearchByPetName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(3, "Mariya", new Cat("Pushok")));

        CLINIC_CACHE.add(1, "Mariya", "Cat", "Snezhok");
        CLINIC_CACHE.add(2, "Igor", "Dog", "Killer");
        CLINIC_CACHE.add(3, "Mariya", "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("owner")).thenReturn("");
        when(req.getParameter("pet")).thenReturn("Pushok");

        SearchClientServlet search = new SearchClientServlet();
        search.doPost(req, resp);
        verify(req, atLeast(1)).getParameter("owner");
        verify(req, atLeast(1)).getParameter("pet");

        assertEquals(search.getSearchResult(), clients);
        CLINIC_CACHE.removeAll();
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }
}