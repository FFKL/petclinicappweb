package org.petclinic.models;

import org.petclinic.petclinicapp.Pets.Pet;
import org.petclinic.petclinicapp.Pets.PetCreate;
import org.petclinic.petclinicapp.Pets.PetType;

public class PetModel {

    private int clientId;
    private String petType;
    private String petName;
    private int id;

    public PetModel() {
    }

    public PetModel(int clientId, String petType, String petName) {
        this.clientId = clientId;
        this.petType = petType;
        this.petName = petName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /*public Pet getPet() {
        return PetCreate.createPet(PetType.selectPetType(petType), petName);
    }*/

}