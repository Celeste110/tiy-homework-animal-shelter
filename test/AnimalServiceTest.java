import entity.Animal;
import repository.AnimalRepository;
import service.AnimalsService;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by katherine_celeste on 8/23/16.
 */

// Test AnimalService functionality
public class AnimalServiceTest {

    private AnimalRepository animalRepository = new AnimalRepository("test1.json");
    ;

    private AnimalsService animalsService = new AnimalsService(animalRepository);

    public AnimalServiceTest() throws IOException {
    }

    //mock up w/ one animal - temp mock file??
    // animalRepository = Mockito.mock(AnimalRepository.class); // mock AnimalRepository object
    //animalsService = new AnimalsService (animalRepository); //???  use this object and verify?

    @Test
    /**
     * Given an AnimalsService
     * When the properties of an Animal are received
     * Then an animal is created
     */
    public void whenAnimalInfoReceivedThenCreateAnimal() throws IOException { //example of behavior testing: doesn't check the result of a method call, but rather checks that a method is called with the right parameters
        // Arrange
        String name = "Tammie";
        String species = "Cat";
        String breed = "Tabby";
        String description = "purr";

        // Act
        animalsService.createAnimal(name, species, breed, description);

        // Assert
        assertThat(animalsService.listAnimals().size(), equalTo(1)); // check to see if createAnimal method was invoked successfully
        animalsService.removeAnAnimal(0);
    }

    @Test
    /**
     * Given an AnimalService
     * When an index is received
     * Then an animal is removed from the list
     */
    public void whenIndexProvidedRemoveAnimal() throws IOException {
        // Arrange
        animalsService.createAnimal("Tammie", "Cat", "Tabby", "Cute n' friendly");

        // Act
        animalsService.removeAnAnimal(0);

        // Assert
        assertThat(animalsService.listAnimals().size(), is(0));

    }

    @Test
    /**
     * Given an AnimalService
     * When an index is received
     * Then the animal is retrieved from the list
     */
    public void whenIndexProvidedRetrieveAnimalFromList() throws IOException {
        // Arrange
        animalsService.createAnimal("Tammie", "Cat", "Tabby", "Cute n' friendly");

        // Act
        animalsService.getAnimal(0);

        // Assert
        assertThat(animalsService.getAnimal(0), equalTo(animalsService.listAnimals().get(0)));
        animalsService.removeAnAnimal(0);
    }

    @Test
    /**
     * Given an AnimalService
     * When an index is received
     * Then the animal at the index position in the list is modified
     */
    public void whenIndexProvidedModifyAnimalFromList() throws IOException {
        // Arrange
        animalsService.createAnimal("Tammie", "Cat", "Tabby", "Cute n' friendly");
        String name = animalsService.listAnimals().get(0).getName();

        // Act
        animalsService.modifyAnimal(0, "name", "Rogers");
        String changedName = animalsService.listAnimals().get(0).getName();

        // Assert
        assertThat(name, not(is(changedName)));
        animalsService.removeAnAnimal(0);

    }

    /**
     * Given an AnimalService
     * When application requests the list of animals
     * Then an ArrayList of animals is returned
     */
    @Test
    public void listAnimalsTest() {
        // Arrange
        animalsService.createAnimal("Tammie", "Cat", "Tabby", "Cute n' friendly");

        // Act
        ArrayList<Animal> list = animalsService.listAnimals();

        // Assert
        assertThat(list.size(), is(1));
        animalsService.removeAnAnimal(0);
    }
}
