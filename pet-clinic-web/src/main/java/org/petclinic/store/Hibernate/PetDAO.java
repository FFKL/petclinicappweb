package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.Pet;

public interface PetDAO extends Storage<Pet> {
    public void delete(final String name);
}
