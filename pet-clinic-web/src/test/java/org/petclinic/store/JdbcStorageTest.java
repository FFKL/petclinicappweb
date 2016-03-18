package org.petclinic.store;

import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;
public class JdbcStorageTest {

    /*//// TODO: 07.02.2016 refactor
    @Test
    public void testAdd() throws Exception {
        JdbcStorage storage = new JdbcStorage();
        int previousSize = storage.getClients().size();
        int newSize = previousSize + 1;
        storage.add("Vasili");
        assertEquals(newSize, storage.getClients().size());
        int id = storage.getClients().get(previousSize).getId();
        this.returnToStartCondition(storage, id);
        storage.close();
    }

    private void returnToStartCondition(JdbcStorage storage, int id) {
        String str = "ALTER SEQUENCE client_uid_seq RESTART WITH " + id + "; ALTER SEQUENCE pet_uid_seq RESTART WITH " + id; // restart sequence работает только со строкой
        try (final PreparedStatement statement = storage.getConnection().prepareStatement("DELETE FROM pet WHERE client_id >= ?; DELETE FROM client WHERE uid >= ?;" + str);) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}