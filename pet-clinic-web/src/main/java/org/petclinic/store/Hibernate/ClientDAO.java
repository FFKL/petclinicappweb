package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.Client;

import java.util.List;

public interface ClientDAO extends Storage<Client> {
    public List<Client> getByClientName(final String clientName);
    public List<Client> getClientsByPetName(final String petName);
}
