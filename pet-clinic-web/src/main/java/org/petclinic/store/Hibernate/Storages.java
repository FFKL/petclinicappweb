package org.petclinic.store.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Storages {
    public final ClientDAO clientStorage;
    public final PetDAO petStorage;

    @Autowired
    public Storages(final ClientDAO clientStorage, final PetDAO petStorage) {
        this.clientStorage = clientStorage;
        this.petStorage = petStorage;
    }
}
