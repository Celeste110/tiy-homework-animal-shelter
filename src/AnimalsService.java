import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by katherine_celeste on 8/16/16.
 */
public class AnimalsService {

    private AnimalRepository fileWithAnimalInfo;

    public AnimalsService(AnimalRepository fileWithAnimalInfo) {
        this.fileWithAnimalInfo = fileWithAnimalInfo;
    }

    public ArrayList<Animal> listAnimals() {
        return fileWithAnimalInfo.listAnimals();
    }


    public void createAnimal(String name, String species, String breed, String description) {
        try {
            fileWithAnimalInfo.createAnimal(new Animal(name, species, breed, description));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void removeAnAnimal(int index) {
        try {
            fileWithAnimalInfo.removeAnAnimal(index);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Animal getAnimal(int index) {
        return fileWithAnimalInfo.getAnimal(index);
    }

}
