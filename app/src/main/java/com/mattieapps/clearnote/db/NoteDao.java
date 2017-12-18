package com.mattieapps.clearnote.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mattieapps.clearnote.model.Note;

import java.util.List;

/**
 * Created by andrewmattie on 12/17/17.
 */

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Query("SELECT * from notes where _id LIKE :id")
    Note findById(Long id);

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);
}
