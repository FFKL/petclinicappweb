package org.petclinic.models.Hibernate;

import java.util.Set;

public class Client {
    private final String FORMAT_STRING = "%s%s%s%s%s%s";

    private int id;
    private String clientName;
    private Set<Pet> pets;

    public Client(int id, String clientName, Set<Pet> pets) {
        this.id = id;
        this.clientName = clientName;
        this.pets = pets;
    }

    public Client() {
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, "ID: ", this.getId(), "; ClientName: ", this.getClientName(), "; PetList: ", this.getPets());
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
        Client e = (Client) obj;
        return (this.getId() == e.getId() && this.getClientName() == e.getClientName() && this.getPets().equals(e.getPets()));
    }

    @Override
    public int hashCode() {
        int result = 5;
        result = 31*result + this.getClientName().hashCode();
        result = 31*result + this.getId();
        result = 31*result + this.getPets().hashCode();
        return result;
    }

}
