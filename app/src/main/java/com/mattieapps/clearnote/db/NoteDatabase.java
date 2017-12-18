package com.mattieapps.clearnote.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mattieapps.clearnote.model.Note;

/**
 * Created by andrewmattie on 12/17/17.
 */

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static NoteDatabase getNoteDatabase(Context context) {
        if (INSTANCE == null) {
            //TODO remove allowMainThreadQueries after testing is complete
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note-database").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
