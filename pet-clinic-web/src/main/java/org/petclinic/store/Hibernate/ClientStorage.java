package org.petclinic.store.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.petclinic.models.Hibernate.Client;

import java.util.Collection;

public class ClientStorage {
    private final SessionFactory factory;

    public ClientStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public interface Command<T> {
        T process(Session session);
    }

    private <T> T transaction(final Command<T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }

    public int add(final Client client) {
        return transaction(new Command<Integer>() {
            @Override
            public Integer process(Session session) {
                session.save(client);
                return client.getId();
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
