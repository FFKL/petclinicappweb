package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class UserStorage implements UserDAO {

    private final HibernateTemplate template;

    @Autowired
    public UserStorage(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public User findByAuth(final String login, final String password) {
        List<User> list = (List<User>)this.template.find("from User where login=? and password=?", login, password);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Collection<User> values() {
        return (List<User>) this.template.find("from User");
    }

    @Transactional
    @Override
    public int add(User user) {
        return (int) this.template.save(user);
    }

    @Override
    public void edit(User edit) {

    }

    @Transactional
    @Override
    public void delete(int id) {
        User user = new User();
        user.setId(id);
        this.template.delete(user);
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User findByName(String name) {
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
