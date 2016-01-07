package org.petclinic.petclinicapp.Pets;

public class Dog implements Pet {

    private String name;

    public Dog(final String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return "Gav-Gav";
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
        Pet e = (Pet) obj;
        return (this.getName() == e.getName() && this.getClass() == e.getClass());
    }

    @Override
    public int hashCode() {
        int result = 5;
        result = 31*result + this.name.hashCode();
        result = 31*result + this.getClass().hashCode();
        return result;
    }
}
