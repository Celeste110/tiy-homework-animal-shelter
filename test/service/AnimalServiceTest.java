package service;

import entity.Animal;
import entity.AnimalNotes;
import org.junit.Before;
import repository.AnimalRepository;
import service.AnimalsService;
import org.junit.Test;
import service.NoteService;
import service.TypeService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Created by katherine_celeste on 8/23/16.
 */

//Test AnimalService functionality
public class AnimalServiceTest {

    private AnimalsService animalsService;
    private AnimalRepository mockAnimalRepository;
    private ResultSet mockAnimalListResultSet;
    private NoteService mockNoteService;
    private TypeService mockTypeService;

    @Before
    public void before() throws SQLException {

        // create a mock repository
        mockAnimalRepository = mock(AnimalRepository.class);
        mockNoteService = mock(NoteService.class);
        mockTypeService = mock(TypeService.class);

        animalsService = new AnimalsService(mockAnimalRepository, mockNoteService, mockTypeService);

        // create a mock result set
        mockAnimalListResultSet = mock(ResultSet.class);

        // make listAnimal method return a result set
        when(mockAnimalRepository.listAnimal()).thenReturn(mockAnimalListResultSet);

        // this mocks the getInt() and getString() methods so that they return mock data.
        when(mockAnimalListResultSet.getInt("animal_id")).thenReturn(1);
        when(mockAnimalListResultSet.getString("animal_name")).thenReturn("Grace");
        when(mockAnimalListResultSet.getString("species")).thenReturn("Cat");
        when(mockAnimalListResultSet.getString("breed")).thenReturn("Tabby");
        when(mockAnimalListResultSet.getString("description")).thenReturn("Clever cat");
    }

    @Test
    /**
     * Given a (mock) database with a set of animals
     * When a list of animals is retrieved
     * Then an ArrayList containing some animals is returned
     */
    //example of behavior testing: doesn't check the result of a method call,
    // but rather checks that a method is called with the right parameters
    public void whenAnimalListedThenArrayListNotEmpty() throws IOException, SQLException {
        // Arrange
        when(mockAnimalListResultSet.next()).thenReturn(true).thenReturn(false);

        // Act
        ArrayList<Animal> animals = animalsService.listAnimals();

        // Assert
        assertThat(animals.size()>0, is(true));
    }

    /**
     * Given a (mock) database with no people
     * When a list of animals is retrieved
     * Then an ArrayList containing no people is returned
     */
    @Test
    public void whenNoPeopleListedThenArrayListIsEmpty() throws SQLException {
        // Arrange

        // this mocks the next() method in the animal list result set. It always returns false.
        when(mockAnimalListResultSet.next()).thenReturn(false);

        // Act
        ArrayList<Animal> animals = animalsService.listAnimals();

        // Assert
        assertThat(animals.size(), is(0));
    }

//    @Test
//    public void whenIndexProvidedRemoveAnimal() throws IOException, SQLException {
//        // Arrange
//
//        // Act
//        animalsService.removeAnAnimal(0);
//
//        // Assert
//        assertThat(animalsService.listAnimals().size(), is(0));
//
//    }
//
//    @Test
//    /**
//     * Given an AnimalService
//     * When an index is received
//     * Then the animal is retrieved from the list
//     */
//    public void whenIndexProvidedRetrieveAnimalFromList() throws IOException, SQLException {
//        // Arrange
//        animalsService.createAnimal("Tammie", "Cat", "Tabby", "Cute n' friendly", 0, new ArrayList<>());
//
//
//        // Act
//        Animal testAnimal = animalsService.listAnimals().get(0);
//
//        // Assert
//        assertThat(testAnimal, is(not(null)));
//        animalsService.removeAnAnimal(0);
//    }
//
//    @Test
//    /**
//     * Given an AnimalService
//     * When an index is received
//     * Then the animal at the index position in the list is modified
//     */
//    public void whenIndexProvidedModifyAnimalFromList() throws IOException, SQLException {
//        // Arrange
//        animalsService.createAnimal("Tammie", "Cat", "Tabby", "Cute n' friendly", 0, new ArrayList<>());
//        String name = animalsService.listAnimals().get(0).getName();
//
//        // Act
//        animalsService.modifyAnimal(0, "name", "Rogers", animalsService.listAnimals().get(0));
//        String changedName = animalsService.listAnimals().get(0).getName();
//
//        // Assert
//        assertThat(name, not(is(changedName)));
//        animalsService.removeAnAnimal(0);
//
}
