package entity;

/**
 * Created by katherine_celeste on 8/16/16.
 */
public class Animal {
    private String name = "";
    private String species = "";
    private String breed = "";
    private String description = "";

    public Animal(String name, String species, String breed, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSpecies() {
        return species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
