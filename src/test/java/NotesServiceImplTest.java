import org.example.MockSystemNotesService;
import org.example.Note;
import org.example.NotesServiceImpl;
import org.example.NotesStorage;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


public class NotesServiceImplTest {

    MockSystemNotesService env;
    public NotesServiceImpl notesServiceImpl;
    static Logger log = Logger.getLogger(String.class.getName());


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        log.info("BeforeAll");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        log.info("AfterAll");
    }

    @BeforeEach
    void setUp() throws Exception {
        log.info("BeforeEach");

        env = new MockSystemNotesService();
        notesServiceImpl = new NotesServiceImpl(env);

    }

    @AfterEach
    void tearDown() throws Exception {
        log.info("AfterEach");
        notesServiceImpl = null;
    }


    @Test
    void testIsItAClass() {
        assertEquals(notesServiceImpl.getClass().toString(), "class org.example.NotesServiceImpl");
    }

    @Test
    void testAddingANote() {
        Note note = new Note("note 1", 44.5F);
        notesServiceImpl.add(note);

        assertTrue(env.checkIfNoteAdded(note));
    }

    @Test
    void testAddingANoteAndNotFindingIt() {
        Note note = new Note("note 1", 44.5F);
        Note note2 = new Note("note 1", 43.5F);
        notesServiceImpl.add(note);

        assertFalse(env.checkIfNoteAdded(note2));
    }

    @Test
    void testClearingStorage() {
        Note note = new Note("note 1", 44.5F);
        Note note2 = new Note("note 1", 43.5F);
        notesServiceImpl.add(note);
        notesServiceImpl.add(note2);

        notesServiceImpl.clear();

        assertFalse(env.checkIfStorageIsNotEmpty());
    }

    @Test
    void testNotClearingStorage() {
        Note note = new Note("note 1", 44.5F);
        Note note2 = new Note("note 1", 43.5F);
        notesServiceImpl.add(note);
        notesServiceImpl.add(note2);

        assertTrue(env.checkIfStorageIsNotEmpty());
    }

    @Test
    void testCheckAverageOfNotes() {
        Note note = new Note("note 1", 2.5F);
        Note note2 = new Note("note 1", 3.5F);
        Note note3 = new Note("note 1", 0F);
        Note note4 = new Note("note 2", 2.5F);
        notesServiceImpl.add(note);
        notesServiceImpl.add(note2);
        notesServiceImpl.add(note3);
        notesServiceImpl.add(note4);

        assertEquals(notesServiceImpl.averageOf("note 1"), 2F);
    }

    @Test
    void testCheckAverageOfNotesNoWrites() {
        Note note = new Note("note 1", 2.5F);
        Note note2 = new Note("note 1", 3.5F);
        Note note3 = new Note("note 1", 0F);
        notesServiceImpl.add(note);
        notesServiceImpl.add(note2);
        notesServiceImpl.add(note3);

        assertEquals(notesServiceImpl.averageOf("note 2"), 0.0f/0);
    }

    @Test
    void testCreateWith() {
        notesServiceImpl = NotesServiceImpl.createWith(env);
        Note note = new Note("note 1", 2.5F);
        notesServiceImpl.add(note);

        assertTrue(env.checkIfNoteAdded(note));
    }
}
