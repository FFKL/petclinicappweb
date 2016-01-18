package org.petclinic.petclinicapp.Pets;

public class PetCreate {
    public static Pet createPet(PetType type, String name) {
        switch (type) {
            case CAT:
                return new Cat(name);
            case DOG:
                return new Dog(name);
            default:
                return new DefaultPet(name);
        }
    }
}
