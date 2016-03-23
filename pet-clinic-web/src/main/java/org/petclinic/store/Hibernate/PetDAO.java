package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.Pet;

import java.util.List;

public interface PetDAO extends Storage<Pet> {
    public List<Pet> getByPetName(final String petName);
    public void delete(final String name);
}
