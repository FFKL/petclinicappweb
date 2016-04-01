package org.petclinic.store.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Storages {
    public final ClientDAO clientStorage;
    public final PetDAO petStorage;
    public final UserDAO userStorage;
    public final RoleDAO roleStorage;

    @Autowired
    public Storages(final ClientDAO clientStorage, final PetDAO petStorage, final UserDAO userStorage, final RoleDAO roleStorage) {
        this.clientStorage = clientStorage;
        this.petStorage = petStorage;
        this.userStorage = userStorage;
        this.roleStorage = roleStorage;
    }
}
