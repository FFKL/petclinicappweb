package org.petclinic.petclinicapp.Pets;
/**
 * Питомец
 */
public abstract class Pet {

    private final String FORMAT_STRING = "%s%s%s%s";

    private String petName;

    public Pet(String petName) {
        this.petName = petName;
    }

    /**
     * Получение имени питомца
     * @return Имя питомца
     */
    public String getName() {
        return this.petName;
    };

    public String getPetType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Задание имени питомца
     * @param name Имя питомца
     */
    public void setName(String name) {
        this.petName = name;
    };

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, "PetType: ", this.getPetType(), "; PetName: ", this.getName());
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
        result = 31*result + this.petName.hashCode();
        result = 31*result + this.getClass().hashCode();
        return result;
    }

}
