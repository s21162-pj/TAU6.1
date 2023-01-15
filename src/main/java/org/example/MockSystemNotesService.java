package org.example;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockSystemNotesService implements NotesStorage {

    private Multimap<String, Note> map = ArrayListMultimap.create();

    @Override
    public void add(Note note) {
        map.put(note.getName(), note);
    }

    @Override
    public List<Note> getAllNotesOf(String name) {

        Collection<Note> coll = map.get(name);

        List<Note> list;
        if (coll instanceof List)
            list = (List<Note>)coll;
        else
            list = new ArrayList<Note>(coll);

        return list;
    }

    @Override
    public void clear() {
        map = ArrayListMultimap.create();
    }

    public boolean checkIfNoteAdded(Note note) {
        List<Note> coll = getAllNotesOf(note.getName());

        if (coll.size() != 0) {
            for (Note not : coll) {
                if (not.getNote() == note.getNote()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfStorageIsNotEmpty() {

        if (map.size() != 0) {
            return true;
        }
        return false;
    }

}
