package org.petclinic.models.Hibernate;

public class Pet {
    private final String FORMAT_STRING = "%s%s%s%s";

    private int id;
    private int clientId;
    private String petName;
    private String petType;

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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, "PetType: ", this.getPetType(), "; PetName: ", this.getPetName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pet e = (Pet) obj;
        return (this.getPetName() == e.getPetName() && this.getPetType() == e.getPetType() && this.getClientId() == e.getClientId());
    }

    @Override
    public int hashCode() {
        int result = 5;
        result = 31*result + this.getPetType().hashCode();
        result = 31*result + this.getPetName().hashCode();
        result = 31*result + this.getClientId();
        return result;
    }
}
