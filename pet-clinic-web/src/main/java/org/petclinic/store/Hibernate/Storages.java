package org.petclinic.store.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Storages {
    public final ClientStorage clientStorage;
    public final PetStorage petStorage;

    @Autowired
    public Storages(final ClientStorage clientStorage, final PetStorage petStorage) {
        this.clientStorage = clientStorage;
        this.petStorage = petStorage;
    }
}
