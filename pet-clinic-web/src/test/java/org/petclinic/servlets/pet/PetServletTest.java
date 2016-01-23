package org.petclinic.servlets.pet;

import org.junit.Test;
import org.mockito.Mockito;
import org.petclinic.petclinicapp.Client;
import org.petclinic.petclinicapp.Pets.Cat;
import org.petclinic.servlets.client.EditClientServlet;
import org.petclinic.store.ClinicCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class PetServletTest extends Mockito {

    final ClinicCache CLINIC_CACHE = ClinicCache.getInstance();

    @Test
    public void testAddPet() throws Exception {

    }

    @Test
    public void testEditPetName() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        CLINIC_CACHE.add(2, "Fedor");
        CLINIC_CACHE.addPet(2, "Cat", "Pushok");
        CLINIC_CACHE.add(3, "Fedor");
        CLINIC_CACHE.addPet(3, "Cat", "Pushok");

        assertFalse(CLINIC_CACHE.getClients().isEmpty());

        when(req.getParameter("id")).thenReturn("3");
        when(req.getParameter("name")).thenReturn("Pushok");
        when(req.getParameter("pet name")).thenReturn("Snezhok");
        when(req.getRequestDispatcher("/views/clinic/EditPet.jsp")).thenReturn(requestDispatcher);

        EditPetServlet edit =  new EditPetServlet();

        edit.doGet(req, resp);
        verify(req, atLeast(1)).getParameter("id");
        verify(req, atLeast(1)).getParameter("name");
        verify(req, atLeast(1)).getRequestDispatcher("/views/clinic/EditPet.jsp");

        edit.doPost(req, resp);

        verify(req, atLeast(1)).getParameter("pet name");

        Client c = new Client(3, "Fedor");
        c.addPet("Cat", "Snezhok");
        assertEquals(c, CLINIC_CACHE.getClients().get(1));

        CLINIC_CACHE.removeAll();
        assertTrue(CLINIC_CACHE.getClients().isEmpty());
    }
}