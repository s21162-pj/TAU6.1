package org.example;

public class Note {

    private final String name;
    private final float note;

    public Note(String name, float note) {
        this.name = name;
        this.note = note;
    }

    public float getNote() {
        return this.note;
    }

    public String getName() {
        return this.name;
    }
}
