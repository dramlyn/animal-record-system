package dev.model;

import java.util.Map;
import java.util.Objects;

/**
 * Класс Животное.
 * Хранит в себе отображение, которое построено по следующей логике:
 * Свойство отображается в его значение
 */
public class Animal {
    private Map<String, String> animalProperties;

    public Animal(Map<String, String> animalProperties) {
        this.animalProperties = animalProperties;
    }

    public Animal() {
    }

    public Map<String, String> getAnimalProperties() {
        return animalProperties;
    }

    public void setAnimalProperties(Map<String, String> animalProperties) {
        this.animalProperties = animalProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getAnimalProperties(), animal.getAnimalProperties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimalProperties());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalProperties=" + animalProperties +
                '}';
    }
}


