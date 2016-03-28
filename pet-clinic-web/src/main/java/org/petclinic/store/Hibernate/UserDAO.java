package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.User;

public interface UserDAO extends Storage<User> {
    public User findByAuth(final String login, final String password);

}
