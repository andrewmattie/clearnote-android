package com.mattieapps.clearnote.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.mattieapps.clearnote.model.Note

/**
 * Created by andrewmattie on 12/17/17.
 */

@Dao
interface NoteDao {
    @get:Query("SELECT * FROM notes")
    val all: List<Note>

    @Query("SELECT * from notes where _id LIKE :id")
    fun findById(id: Long?): Note

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)
}
