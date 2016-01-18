package org.petclinic.petclinicapp.Pets;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetTest {

    @Test
    public void testGetName() throws Exception {
        Pet cat = new Cat("Vaska");
        assertEquals(cat.getName(), "Vaska");

    }

    @Test
    public void testGetPetType() throws Exception {
        Pet dog = new Dog("Sharik");
        assertEquals(dog.getPetType(), "Dog");
    }

    @Test
    public void testSetName() throws Exception {
        Pet dog = new Dog("Bull");
        dog.setName("Sharik");
        assertEquals(dog.getName(), "Sharik");
    }

    @Test
    public void testEquals() throws Exception {
        Pet pet1 = new Cat("Pushok");
        Pet pet2 = new Cat("Pushok");
        Pet pet3 = null;
        Pet pet4 = new Dog("Pushok");

        assertEquals(pet1, pet2);

        assertFalse(pet1.equals(pet3));
        assertFalse(pet1.equals(pet4));
        assertTrue(pet1.equals(pet1));
    }

    @Test
    public void testHashCode() throws Exception {
        Pet pet1 = new Cat("Pushok");
        Pet pet2 = new Cat("Pushok");

        assertEquals(pet1.hashCode(), pet2.hashCode());

    }

    @Test
    public void testToString() throws Exception {
        Pet cat = new Cat("Vaska");
        assertEquals(cat.toString(), "PetType: Cat; PetName: Vaska");
    }
}