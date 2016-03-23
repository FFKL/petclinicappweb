package org.petclinic.models.Hibernate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Pet {
    private final String FORMAT_STRING = "%s%s%s%s%s%s";

    private int id;
    private Client client;
    private String petName;
    private String petType;

    public Pet() {
    }

    public Pet(Client client, String petName, String petType) {
        this.client = client;
        this.petName = petName;
        this.petType = petType;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING,"ID: ", this.getId(), "PetType: ", this.getPetType(), "; PetName: ", this.getPetName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pet)) {
            return false;
        }
        Pet that = (Pet) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(id, that.id);
        equalsBuilder.append(petName, that.petName);
        equalsBuilder.append(petType, that.petType);
        return equalsBuilder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(id);
        hashCodeBuilder.append(petName);
        hashCodeBuilder.append(petType);
        return hashCodeBuilder.toHashCode();
    }
}
