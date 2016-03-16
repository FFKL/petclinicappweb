package org.petclinic.store.Hibernate;

import java.util.Collection;

public interface Storage<T> {
    public Collection<T> values();

    public int add(final T add);

    public void edit(final T edit);

    public void delete(final int id);

    public T get(final int id);

    public T findByName(final String name) ;

    public int generateId();

    public void close();
}
