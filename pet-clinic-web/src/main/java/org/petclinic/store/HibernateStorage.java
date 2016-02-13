package org.petclinic.store;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.petclinic.models.PetModel;
import org.petclinic.petclinicapp.Client;

import java.util.ArrayList;
import java.util.List;

public class HibernateStorage implements Storage {

    private final SessionFactory factory;

    public HibernateStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();

    }

    @Override
    public void add(String clientName) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new Client(0, clientName));
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void addPet(int id, String petType, String petName) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new PetModel(id, petType, petName));
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void editClientName(int id, String clientName) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("update Client as client set client.clientName=:name where client.id=:id");
            query.setString("name", clientName);
            query.setInteger("id", id);
            query.executeUpdate();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void editPetName(int id, String currentPetName, String newPetName) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("update PetModel as pet set pet.petName=:name where pet.clientId=:client_id and pet.petName=:currentName");
            query.setString("name", newPetName);
            query.setInteger("client_id", id);
            query.setString("currentName", currentPetName);
            query.executeUpdate();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Client> searchByPetName(String petName) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("select clientId from PetModel where petName=:name");
            query.setString("name", petName);
            List<Integer> ids = query.list();
            List<Client> clients = new ArrayList<>();
            for (Integer i : ids)
                clients.add(this.searchById(i));
            return clients;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Client> searchByClientName(String clientName) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Client where clientName=:name");
            query.setString("name", clientName);
            List<Client> clients = query.list();
            for (Client c : clients) {
                List<PetModel> pets = getPets(c.getId());
                for (PetModel p : pets)
                    c.addPet(p.getPetType(), p.getPetName());
            }
            return clients;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Client searchById(int id) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Client client = (Client)session.get(Client.class, id);
            List<PetModel> pets = getPets(id);
            for (PetModel p : pets)
                client.addPet(p.getPetType(), p.getPetName());
            return client;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public List<PetModel> getPets(int id) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from PetModel where clientId=:id");
            query.setInteger("id", id);
            return query.list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delClient(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new Client(id, null));
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delPet(int id, String petName) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("delete from PetModel where clientId=:id and petName=:name");
            query.setInteger("id", id);
            query.setString("name", petName);
            query.executeUpdate();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Client> getClients() {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Client> clients = session.createQuery("from Client").list();
            for (Client c : clients) {
                List<PetModel> pets = getPets(c.getId());
                for (PetModel p : pets)
                    c.addPet(p.getPetType(), p.getPetName());
            }
            return clients;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void removeAll() {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
           final Query query = session.createSQLQuery("delete from Pet; delete from Client; ALTER SEQUENCE client_uid_seq RESTART WITH 1; ALTER SEQUENCE pet_uid_seq RESTART WITH 1");
            query.executeUpdate();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public boolean isEmpty() {
        if (this.getClients().size() == 0)
            return true;
        return false;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
