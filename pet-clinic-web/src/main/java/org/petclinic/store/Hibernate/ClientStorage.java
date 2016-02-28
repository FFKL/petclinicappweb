package org.petclinic.store.Hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.petclinic.models.Hibernate.Client;

import java.util.Collection;
import java.util.List;

public class ClientStorage extends BaseStorage {

    public int add(final Client client) {
        return transaction(new Command<Integer>() {
            @Override
            public Integer process(Session session) {
                session.save(client);
                return client.getId();
            }
        });
    }

    public void edit(final Client client) {
        transaction(new Command() {
            @Override
            public Object process(Session session) {
                session.update(client);
                return null;
            }
        });
    }

    public void delete(final int id) {
        transaction(new Command() {
            @Override
            public Object process(Session session) {
                session.delete(getById(id));
                return null;
            }
        });
    }

    public Client getById(final int id) {
        return transaction(new Command<Client>() {
            @Override
            public Client process(Session session) {
                return (Client) session.get(Client.class, id);
            }
        });
    }

    public Client getByClientName(final String clientName) {
        return transaction(new Command<Client>() {
            @Override
            public Client process(Session session) {
                final Query query = session.createQuery("from Client as client where client.clientName=:clientName");
                query.setString("clientName", clientName);
                return (Client) query.iterate().next();
            }
        });
    }

    public List<Client> searchByClientName(final String clientName) {
        return transaction(new Command<List<Client>>() {
            @Override
            public List<Client> process(Session session) {
                final Query query = session.createQuery("from Client as client where lower(client.clientName) like lower(concat('%',:clientName,'%'))");
                query.setString("clientName", clientName);
                return query.list();
            }
        });
    }


    public Collection<Client> values() {
        return transaction(new Command<Collection<Client>>() {
            @Override
            public Collection<Client> process(Session session) {
                return session.createQuery("from Client").list();
            }
        });
    }
}
