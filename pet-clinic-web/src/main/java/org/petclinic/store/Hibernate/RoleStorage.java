package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class RoleStorage implements RoleDAO {

    private final HibernateTemplate template;

    @Autowired
    public RoleStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Role> values() {
        return (List<Role>) this.template.find("from Role");
    }

    @Override
    public int add(Role role) {
        return 0;
    }

    @Override
    public void edit(Role edit) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Role get(int id) {
        return (Role) this.template.get(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }
}
