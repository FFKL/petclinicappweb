package org.petclinic.models.Hibernate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Client)) {
            return false;
        }
        Client that = (Client) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(id, that.id);
        equalsBuilder.append(clientName, that.clientName);
        equalsBuilder.append(pets, that.pets);
        return equalsBuilder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(id);
        hashCodeBuilder.append(clientName);
        hashCodeBuilder.append(pets);
        return hashCodeBuilder.toHashCode();
    }

}
