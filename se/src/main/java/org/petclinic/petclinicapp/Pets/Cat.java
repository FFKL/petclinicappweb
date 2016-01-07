package org.petclinic.petclinicapp.Pets;

public class Cat implements Pet {

    private String name;
    /**
     * Конструктор
     */
    public Cat(final String name){
        this.name = name;
    }
    /**
     * Получение имени питомца
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * Добавление имени питомца
     * @param name Имя питомца
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Звук, издаваемый питомцем
     */
    @Override
    public String makeSound() {
        return "Meow-Meow";
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
