package org.petclinic.store.Hibernate;


import org.petclinic.models.Hibernate.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class ClientStorage implements ClientDAO {

    private final HibernateTemplate template;

    @Autowired
    public ClientStorage(final org.springframework.orm.hibernate4.HibernateTemplate template) {
        this.template = template;
    }


    @Transactional
    @Override
    public int add(final Client client) {
        return (int) this.template.save(client);
    }

    @Transactional
    public void edit(final Client client) {
        this.template.update(client);
    }

    @Transactional
    public void delete(final int id) {
        Client client = new Client();
        client.setId(id);
        this.template.delete(client);
    }

    @Override
    public Client get(int id) {
        return (Client) this.template.get(Client.class, id);
    }

    @Override
    public Client findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }

    public List<Client> getByClientName(final String clientName) {
        return (List<Client>) this.template.find("from Client where name=?", clientName);
    }

    public List<Client> searchByClientName(final String clientName) {
        return null;
    }


    public Collection<Client> values() {
        return (List<Client>) this.template.find("from Client");
    }
}
