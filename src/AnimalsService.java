import java.util.ArrayList;

/**
 * Created by katherine_celeste on 8/16/16.
 */
public class AnimalsService {

    private ArrayList<Animal> animals = new ArrayList<>();

    public ArrayList<Animal> listAnimals() {
        return animals;
    }

    // Creates a new Animal and adds it to the list
    public void createAnimal(String name, String species, String breed, String description) {
        this.animals.add(new Animal(name, species, breed, description));
    }

    public void removeAnAnimal(int index) {
        animals.remove(index);
    }

}
