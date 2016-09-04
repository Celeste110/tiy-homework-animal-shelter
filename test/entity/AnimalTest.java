package entity;
import org.junit.Before;
import org.junit.Test;
import repository.AnimalRepository;
import repository.AnimalTypeRepository;
import service.TypeService;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.mock;


/**
 * Created by katherine_celeste on 8/23/16.
 */
public class AnimalTest {

    private Animal animal;
    private AnimalTypeRepository mockAnimalTypeRepository;

    @Before
    public void beforeEachTest() throws SQLException {

        mockAnimalTypeRepository = mock(AnimalTypeRepository.class);
        String jdbcUrl = "jdbc:postgresql://localhost:5432/animals";
        animal = new Animal("Coco", 1, "Tabby", "Adorable", 0, new ArrayList<>(), new TypeService(mockAnimalTypeRepository));
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

        // Act
        animal.setSpecies(1);

        // Assert
        assertThat(animal.getSpecies(), equalTo(1));
    }

    @Test
    /**
     * Given an Animal
     * When a species is received
     * Then the animal species is retrieved
     */
    public void whenSpeciesReceivedThenGetAnimalSpecies() {
        // Arrange
        animal.setSpecies(1);

        // Act

        // Assert
        assertThat(animal.getSpecies(), equalTo(1));
    }

    @Test
    /**
     * Given an Animal
     * When a breed is received
     * Then the animal breed is set
     */
    public void whenBreedReceivedThenSetAnimalBreed() {
        // Arrange
        String breed = "";

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
        animal.setBreed("Parakeet");

        // Act
        String returnedName = animal.getBreed();

        // Assert
        assertThat(returnedName, equalTo("Parakeet"));
    }

    @Test
    /**
     * Given an Animal
     * When a description is received
     * Then the animal description is set
     */
    public void whenDescriptionReceivedThenSetAnimalDescription() {
        // Arrange
        String description = "Cute!";

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
        animal.setDescription("Small");

        // Act
        String returnedName = animal.getDescription();

        // Assert
        assertThat(returnedName, equalTo("Small"));
    }

}
