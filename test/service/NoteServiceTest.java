package service;

import entity.AnimalNotes;
import org.junit.Before;
import org.junit.Test;
import repository.NoteRepository;

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
public class NoteServiceTest {
    private NoteService noteService;
    private NoteRepository mockNoteRepository;
    private ResultSet mockNoteResultSet;

    @Before
    public void before() throws SQLException, IOException {

        // create a mock repository
        mockNoteRepository = mock(NoteRepository.class);

        noteService = new NoteService(mockNoteRepository);

        // create a mock result set
        mockNoteResultSet = mock(ResultSet.class);

        // make listAnimal method return a result set
        when(mockNoteRepository.listNotes()).thenReturn(mockNoteResultSet);

        // this mocks the getInt() and getString() methods so that they return mock data.
        when(mockNoteResultSet.getInt("animal_id")).thenReturn(24);
        when(mockNoteResultSet.getString("note_text")).thenReturn("Current on all vaccinations");
        when(mockNoteResultSet.getString("date_time")).thenReturn("2016-09-04");
    }

    @Test
    /**
     * Given a (mock) database with a list of notes
     * When a list of notes is retrieved
     * Then an ArrayList containing notes is returned
     */
    //example of behavior testing: doesn't check the result of a method call,
    // but rather checks that a method is called with the right parameters
    public void whenNotesListedThenArrayListNotEmpty() throws IOException, SQLException {
        // Arrange
        when(mockNoteResultSet.next()).thenReturn(true).thenReturn(false);

        // Act
        ArrayList<AnimalNotes> notes = noteService.listNotes();

        // Assert
        assertThat(notes.size()>0, is(true));
    }

    /**
     * Given a (mock) database with a list of notes
     * When a list of notes is retrieved
     * Then an ArrayList containing no notes is returned
     */
    @Test
    public void whenNoNotesListedThenArrayListIsEmpty() throws SQLException {
        // Arrange

        // this mocks the next() method in the note list result set. It always returns false.
        when(mockNoteResultSet.next()).thenReturn(false);

        // Act
        ArrayList<AnimalNotes> notes = noteService.listNotes();

        // Assert
        assertThat(notes.size(), is(0));
    }

}
