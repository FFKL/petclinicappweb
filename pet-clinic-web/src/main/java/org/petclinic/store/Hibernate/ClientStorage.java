package org.petclinic.store.Hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
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
        /*return transaction(new Command<Integer>() {
            @Override
            public Integer process(Session session) {
                session.save(client);
                return client.getId();
            }
        });*/

        return (int) this.template.save(client);
    }

    public void edit(final Client client) {
        /*transaction(new Command() {
            @Override
            public Object process(Session session) {
                session.update(client);
                return null;
            }
        });*/
    }

    @Transactional
    public void delete(final int id) {
        /*transaction(new Command() {
            @Override
            public Object process(Session session) {
                session.delete(getById(id));
                return null;
            }
        });*/
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

    public Client getById(final int id) {
        /*return transaction(new Command<Client>() {
            @Override
            public Client process(Session session) {
                return (Client) session.get(Client.class, id);
            }
        });*/
        return null;
    }

    public Client getByClientName(final String clientName) {
        /*return transaction(new Command<Client>() {
            @Override
            public Client process(Session session) {
                final Query query = session.createQuery("from Client as client where client.clientName=:clientName");
                query.setString("clientName", clientName);
                return (Client) query.iterate().next();
            }
        });*/
        return null;
    }

    public List<Client> searchByClientName(final String clientName) {
        /*return transaction(new Command<List<Client>>() {
            @Override
            public List<Client> process(Session session) {
                final Query query = session.createQuery("from Client as client where lower(client.clientName) like lower(concat('%',:clientName,'%'))");
                query.setString("clientName", clientName);
                return query.list();
            }
        });*/
        return null;
    }


    public Collection<Client> values() {
        /*return transaction(new Command<Collection<Client>>() {
            @Override
            public Collection<Client> process(Session session) {
                return session.createQuery("from Client").list();
            }
        });*/
        return (List<Client>) this.template.find("from Client");
    }
}
