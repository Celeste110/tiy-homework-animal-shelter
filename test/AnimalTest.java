import entity.Animal;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


/**
 * Created by katherine_celeste on 8/23/16.
 */
public class AnimalTest {

    private Animal animal;

    @Before
    public void beforeEachTest() {
        animal = new Animal("Coco", "Cat", "Tabby", "Adorable");
    }

    @Test
    /**
     * Given an Animal
     * When a name is received
     * Then the animal name is set
     */
    public void whenNameReceivedThenSetAnimalName() {
        // Arrange
        String name = "Carl";

        // Act
        animal.setName(name);

        // Assert
        assertThat(animal.getName(), equalTo(name));
    }

    @Test
    /**
     * Given an Animal
     * When a name is received
     * Then the animal name is retrieved
     */
    public void whenNameReceivedThenGetAnimalName() {
        // Arrange
        animal.setName("Carl");

        // Act
        String returnedName = animal.getName();

        // Assert
        assertThat(returnedName, equalTo("Carl"));

    }

    @Test
    /**
     * Given an Animal
     * When a species is received
     * Then the animal species is set
     */
    public void whenSpeciesReceivedThenSetAnimalSpecies() {
        // Arrange
        String species = "Cat";

        // Act
        animal.setSpecies(species);

        // Assert
        assertThat(animal.getSpecies(), equalTo(species));
    }

    @Test
    /**
     * Given an Animal
     * When a species is received
     * Then the animal species is retrieved
     */
    public void whenSpeciesReceivedThenGetAnimalSpecies() {
        // Arrange
        animal.setSpecies("Cat");

        // Act
        String returnedName = animal.getSpecies();

        // Assert
        assertThat(returnedName, equalTo("Cat"));
    }

    @Test
    /**
     * Given an Animal
     * When a breed is received
     * Then the animal breed is set
     */
    public void whenBreedReceivedThenSetAnimalBreed() {
        // Arrange
        String breed = "Tabby";

        // Act
        animal.setBreed(breed);

        // Assert
        assertThat(animal.getBreed(), equalTo(breed));
    }

    @Test
    /**
     * Given an Animal
     * When a breed is received
     * Then the animal breed is retrieved
     */
    public void whenBreedReceivedThenGetAnimalBreed() {
        // Arrange
        animal.setBreed("Tabby");

        // Act
        String returnedName = animal.getBreed();

        // Assert
        assertThat(returnedName, equalTo("Tabby"));
    }

    @Test
    /**
     * Given an Animal
     * When a description is received
     * Then the animal description is set
     */
    public void whenDescriptionReceivedThenSetAnimalDescription() {
        // Arrange
        String description = "Cute n' furry";

        // Act
        animal.setDescription(description);

        // Assert
        assertThat(animal.getDescription(), equalTo(description));
    }

    @Test
    /**
     * Given an Animal
     * When a description is received
     * Then the animal description is retrieved
     */
    public void whenDescriptionReceivedThenGetAnimalDescription() {
        // Arrange
        animal.setDescription("Cute n' furry");

        // Act
        String returnedName = animal.getDescription();

        // Assert
        assertThat(returnedName, equalTo("Cute n' furry"));
    }

}
