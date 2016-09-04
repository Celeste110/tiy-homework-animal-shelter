package service;
import org.junit.Before;
import org.junit.Test;
import repository.AnimalTypeRepository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by katherine_celeste on 9/3/16.
 */
public class TypeServiceTest {
    private TypeService typeService;
    private AnimalTypeRepository mockAnimalTypeRepository;
    private ResultSet mockAnimalTypeResultSet;

    @Before
    public void before() throws SQLException, IOException {

        // create a mock repository
        mockAnimalTypeRepository = mock(AnimalTypeRepository.class);

        typeService = new TypeService(mockAnimalTypeRepository);

        // create a mock result set
        mockAnimalTypeResultSet = mock(ResultSet.class);

        // make listAnimal method return a result set
        when(mockAnimalTypeRepository.listTypes()).thenReturn(mockAnimalTypeResultSet);

        // this mocks the getInt() and getString() methods so that they return mock data.
        when(mockAnimalTypeResultSet.getInt("animal_type_id")).thenReturn(87);
        when(mockAnimalTypeResultSet.getString("animal_types")).thenReturn("Fish");

    }


    @Test
    /**
     * Given a (mock) database with a set of animal types
     * When a list of animal types is retrieved
     * Then an ArrayList containing animal types is returned
     */
    //example of behavior testing: doesn't check the result of a method call,
    // but rather checks that a method is called with the right parameters
    public void whenAnimalTypesListedThenArrayListNotEmpty() throws IOException, SQLException {
        // Arrange
        when(mockAnimalTypeResultSet.next()).thenReturn(true).thenReturn(false);

        // Act
        ArrayList<String> types = typeService.listTypes();

        // Assert
        assertThat(types.size()>0, is(true));
    }

    /**
     * Given a (mock) database with no animal types
     * When a list of animal types is retrieved
     * Then an ArrayList containing no animal types is returned
     */
    @Test
    public void whenNoAnimalTypesListedThenArrayListIsEmpty() throws SQLException {
        // Arrange

        // this mocks the next() method in the people list result set. It always returns false.
        when(mockAnimalTypeResultSet.next()).thenReturn(false);

        // Act
        ArrayList<String> types = typeService.listTypes();

        // Assert
        assertThat(types.size(), is(0));
    }

}
