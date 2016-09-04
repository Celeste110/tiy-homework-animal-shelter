package entity;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by katherine_celeste on 9/2/16.
 */
public class AnimalNotesTest {

        private AnimalNotes note;

        @Before
        public void beforeEachTest() {
            note = new AnimalNotes(0, "Next checkup on 09/08", "09/02", 20);
        }

        @Test
        /**
         * Given a Note object
         * When an animalID is received
         * Then the animalID is set
         */
        public void whenAnimalIDReceivedThenSetAnimalID() {
            // Arrange
            int id = 7;

            // Act
            note.setID(id);

            // Assert
            assertThat(note.getID(), equalTo(7));
        }

        @Test
        /**
         * Given a Note object
         * When an animalID is received
         * Then the animalID is retrieved
         */
        public void whenAnimalIDReceivedThenGetAnimalID() {
            // Arrange
            note.setID(22);

            // Act
            int animalID = note.getID();

            // Assert
            assertThat(animalID, equalTo(22));

        }

        @Test
        /**
         * Given a Note object
         * When a noteID is received
         * Then the noteID is set
         */
        public void whenNoteIDReceivedThenSetNoteID() {
            // Arrange
            int noteID = 9;

            // Act
            note.setNoteID(noteID);

            // Assert
            assertThat(note.getNoteID(), equalTo(9));
        }

        @Test
        /**
         * Given a Note object
         * When a species is received
         * Then the animal species is retrieved
         */
        public void whenNoteIDReceivedThenGetNoteID() {
            // Arrange
            note.setNoteID(4);

            // Act
            int num = note.getNoteID();

            // Assert
            assertThat(num, equalTo(4));
        }

        @Test
        /**
         * Given a Note object
         * When note text is received
         * Then the note text is set
         */
        public void whenNoteTextReceivedThenSetNoteText() {
            // Arrange
            String aNote = "Checkup went well.";

            // Act
            note.setText(aNote);

            // Assert
            assertThat(note.getText(), equalTo(aNote));
        }

        @Test
        /**
         * Given a Note object
         * When note text is received
         * Then the note text is retrieved
         */
        public void whenNoteTextReceivedThenGetNoteText() {
            // Arrange
            note.setText("Testing:)");

            // Act
            String text = note.getText();

            // Assert
            assertThat(text, equalTo("Testing:)"));
        }

    @Test
    /**
     * Given a Note object
     * When a date is received
     * Then the date is set
     */
    public void whenDateReceivedThenSetDate() {
        // Arrange
        String aNote = "1991-03-29";

        // Act
        note.setDate(aNote);

        // Assert
        assertThat(note.getDate(), equalTo(aNote));
    }

    @Test
    /**
     * Given a Note object
     * When a date is received
     * Then the date is retrieved
     */
    public void whenDateReceivedThenGetDate() {
        // Arrange
        note.setDate("2001-09-24");

        // Act
        String date = note.getDate();

        // Assert
        assertThat(date, equalTo("2001-09-24"));
    }

    }
