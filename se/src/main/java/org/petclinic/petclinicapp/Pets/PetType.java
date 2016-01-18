package org.petclinic.petclinicapp.Pets;

public enum PetType {
    CAT, DOG, DEFAULT_PET;

    private static final String CAT_SELECT = "Cat";
    private static final String DOG_SELECT = "Dog";

    public static PetType selectPetType(String petType) {
        switch (petType) {
            case CAT_SELECT:
                return CAT;
            case DOG_SELECT:
                return DOG;
            default:
                return DEFAULT_PET;
        }
    }


}
