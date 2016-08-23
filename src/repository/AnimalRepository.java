package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Animal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Created by katherine_celeste on 8/20/16.
 */
public class AnimalRepository {

    private Path filePath;

    private ArrayList<Animal> animals = new ArrayList<>();

    public AnimalRepository(String fileName) throws IOException {
        filePath = Paths.get(fileName);

        if (Files.exists(filePath)) {
            String json = new String(Files.readAllBytes(filePath)); // 1, this is our JSON
            Type listType = new TypeToken<ArrayList<Animal>>() {}.getType(); // 2. this describes the structure of data in the JSON string. Take note of the generics
            animals = new Gson().fromJson(json, listType); // 3. convert the JSON back to an ArrayList of Animals
        }
    }

    private void persists() throws IOException {
        String json = new Gson().toJson(animals);
        Files.write(filePath, json.getBytes());
    }

    // This method returns a list of all animals
    public ArrayList<Animal> listAnimals() {
        return animals;
    }

    // Creates a new entity.Animal and adds it to the list
    public void createAnimal(Animal animal) throws IOException {
        animals.add(animal);
        persists();  // Must update the repository since info is being modified (added)
    }

    // Removes an entity.Animal from the list
    public void removeAnAnimal(int index) throws IOException {
        animals.remove(index);
        persists(); // Must update the repository since info is being modified (deleted)
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public void modifyAnimal(int index, String property, String newInput) throws IOException
    {
        Animal a = getAnimal(index);
        switch (property){
            case "name":
                a.setName(newInput);
                break;
            case "species":
                a.setSpecies(newInput);
                break;
            case "breed":
                a.setBreed(newInput);
                break;
            case "description":
                a.setDescription(newInput);
                break;
        }
        persists();
    }
}
