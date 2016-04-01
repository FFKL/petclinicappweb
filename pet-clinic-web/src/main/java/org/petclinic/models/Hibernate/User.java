package org.petclinic.models.Hibernate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class User {

    private final String FORMAT_STRING = "%s%s%s%s%s%s%s%s%s%s";

    private int id;
    private String login;
    private Role role;
    private int clientId;
    private String password;

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, "ID: ", this.getId(), "; Login: ", this.getLogin(), "; Password: ", this.getPassword(), "; ClientId: ", this.getClientId(), "; Role: ", this.getRole());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User that = (User) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(id, that.id);
        equalsBuilder.append(login, that.login);
        equalsBuilder.append(password, that.password);
        equalsBuilder.append(clientId, that.clientId);
        equalsBuilder.append(role, that.role);
        return equalsBuilder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(id);
        hashCodeBuilder.append(login);
        hashCodeBuilder.append(password);
        hashCodeBuilder.append(clientId);
        hashCodeBuilder.append(role);
        return hashCodeBuilder.toHashCode();
    }
}
