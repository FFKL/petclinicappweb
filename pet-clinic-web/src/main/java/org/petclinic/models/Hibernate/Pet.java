package org.petclinic.models.Hibernate;

public class Pet {
    private final String FORMAT_STRING = "%s%s%s%s";

    private int id;
    private Client client;
    private String petName;
    private String petType;

    /*public Pet() {
    }

    public Pet(Client client, String petName, String petType) {
        this.client = client;
        this.petName = petName;
        this.petType = petType;
    }*/

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
        return String.format(FORMAT_STRING, "PetType: ", this.getPetType(), "; PetName: ", this.getPetName());
    }

    /*@Override
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
        return (this.getPetName() == e.getPetName() && this.getPetType() == e.getPetType() && this.getClient() == e.getClient());
    }

    @Override
    public int hashCode() {
        int result = 5;
        result = 31*result + this.getPetType().hashCode();
        result = 31*result + this.getPetName().hashCode();
        return result;
    }*/
}
