package org.petclinic.store.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BaseStorage {
    private final SessionFactory factory;

    public BaseStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public interface Command<T> {
        T process(Session session);
    }

    public <T> T transaction(final Command<T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }
}
