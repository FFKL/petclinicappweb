package org.petclinic.servlets.client;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.petclinic.petclinicapp.Client;
import org.petclinic.store.ClinicCache;
import org.petclinic.store.MemoryStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ClientServletTest extends Mockito {

    final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Before
    public void setUp() throws Exception {
        CLINIC_CACHE.setStorage(new MemoryStorage());
    }

    @Test
    public void testAddClient() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("client name")).thenReturn("Fedor");
        when(req.getParameter("type")).thenReturn("Cat");
        when(req.getParameter("pet name")).thenReturn("Pushok");

        assertTrue(CLINIC_CACHE.getClients().isEmpty());

        new AddClientServlet().doPost(req, resp);

        verify(req, atLeast(1)).getParameter("id");
        verify(req, atLeast(1)).getParameter("client name");
        verify(req, atLeast(1)).getParameter("type");
        verify(req, atLeast(1)).getParameter("pet name");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        CLINIC_CACHE.delClient(CLINIC_CACHE.searchByClientName("Fedor").get(0).getId());
    }

    @Test
    public void testDelClient() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        CLINIC_CACHE.removeAll();
        CLINIC_CACHE.add("Fedor");
        CLINIC_CACHE.addPet(1, "Cat", "Pushok");
        when(req.getParameter("id")).thenReturn("1");
        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        new DeleteClientServlet().doGet(req, resp);
        verify(req, atLeast(1)).getParameter("id");
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testEditClientName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        CLINIC_CACHE.add("Fedor");
        CLINIC_CACHE.addPet(1, "Cat", "Pushok");
        CLINIC_CACHE.add("Fedor");
        CLINIC_CACHE.addPet(2, "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("id")).thenReturn("2");
        when(req.getParameter("client name")).thenReturn("Grigoriy");
        when(req.getRequestDispatcher("/views/clinic/ClientView.jsp")).thenReturn(requestDispatcher);

        new ClientViewServlet().doGet(req, resp);
        verify(req, atLeast(1)).getParameter("id");

        new EditClientServlet().doPost(req, resp);
        verify(req, atLeast(1)).getParameter("client name");

        Client c = new Client(2, "Grigoriy");
        c.addPet("Cat", "Pushok");
        assertEquals(c, CLINIC_CACHE.getClients().get(1));

        CLINIC_CACHE.removeAll();

        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testSearchByClientName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(1, "Mariya"));
        clients.get(0).addPet("Cat", "Snezhok");
        clients.add(new Client(3, "Mariya"));
        clients.get(1).addPet("Cat", "Pushok");

        CLINIC_CACHE.add("Mariya");
        CLINIC_CACHE.addPet(1, "Cat", "Snezhok");
        CLINIC_CACHE.add("Igor");
        CLINIC_CACHE.addPet(2, "Dog", "Killer");
        CLINIC_CACHE.add("Mariya");
        CLINIC_CACHE.addPet(3, "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("client name")).thenReturn("Mariya");
        when(req.getParameter("pet name")).thenReturn("");

        SearchClientServlet search = new SearchClientServlet();
        search.doPost(req, resp);
        verify(req, atLeast(1)).getParameter("client name");
        verify(req, atLeast(1)).getParameter("pet name");

        assertEquals(search.getSearchResult(), clients);

        CLINIC_CACHE.removeAll();
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }

    @Test
    public void testSearchByPetName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client(3, "Mariya"));
        clients.get(0).addPet("Cat", "Pushok");

        CLINIC_CACHE.add("Mariya");
        CLINIC_CACHE.addPet(1, "Cat", "Snezhok");
        CLINIC_CACHE.add("Igor");
        CLINIC_CACHE.addPet(2, "Dog", "Killer");
        CLINIC_CACHE.add("Mariya");
        CLINIC_CACHE.addPet(3, "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("client name")).thenReturn("");
        when(req.getParameter("pet name")).thenReturn("Pushok");

        SearchClientServlet search = new SearchClientServlet();
        search.doPost(req, resp);
        verify(req, atLeast(1)).getParameter("client name");
        verify(req, atLeast(1)).getParameter("pet name");

        assertEquals(search.getSearchResult(), clients);
        CLINIC_CACHE.removeAll();
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }
}