package com.mattieapps.clearnote

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mattieapps.clearnote.db.NoteDatabase
import com.mattieapps.clearnote.model.Note
import kotlinx.android.synthetic.main.activity_new.*


/**
 * Created by andrewmattie on 12/17/17.
 */
class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        saveNoteFAB.setOnClickListener {
            val note = Note()
            val saveNote = SaveNoteAsync(NoteDatabase.getNoteDatabase(applicationContext), note)

            note.title = titleEditText.text.toString()
            note.body = bodyEditText.text.toString()
            note.archived = false

            saveNote.execute()

            val intent = Intent(applicationContext, MainActivity::class.java)
            applicationContext.startActivity(intent)
        }
    }
}

private class SaveNoteAsync internal constructor(private val db: NoteDatabase, val note: Note = Note()) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void): Void? {
        db.noteDao().insert(note)
        return null
    }
}