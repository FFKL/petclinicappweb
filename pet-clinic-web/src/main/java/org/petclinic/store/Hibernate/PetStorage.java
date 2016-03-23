package org.petclinic.store.Hibernate;

import org.petclinic.models.Hibernate.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class PetStorage implements PetDAO {
    private final HibernateTemplate template;

    @Autowired
    public PetStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Pet> values() {
        return (List<Pet>) this.template.find("from Pet");
    }

    @Transactional
    @Override
    public int add(Pet pet) {
        return (int) this.template.save(pet);
    }

    @Transactional
    @Override
    public void edit(Pet edit) {
        this.template.update(edit);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Pet pet = new Pet();
        pet.setId(id);
        this.template.delete(pet);
    }

    @Transactional
    public void delete(final String name) {
        this.template.delete(new Pet(null, name, null));
    }

    @Transactional
    @Override
    public Pet get(int id) {
        return (Pet) this.template.get(Pet.class, id);
    }

    @Override
    public Pet findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }

    public List<Pet> getByPetName(final String petName) {
        return (List<Pet>) this.template.find("from Client where name=?", petName);
    }
}
