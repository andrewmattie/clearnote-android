package com.mattieapps.clearnote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val notesArrayList: List<Note>
//        val db = NoteDatabase.getNoteDatabase(applicationContext)
//        val newNote = Note()
//        newNote.title = "New note here"
//        newNote.body = "this is the body"
//
//        db.noteDao().insert(newNote)
//        notesArrayList = db.noteDao().all
//
//        Log.e(TAG, notesArrayList.size.toString())
//        for (note in notesArrayList) {
//            Log.e(TAG, "T: ${note.title} B: ${note.body} $note")
//        }

        newNoteFAB.setOnClickListener {
            val intent = Intent(applicationContext, NewNoteActivity::class.java)
            applicationContext.startActivity(intent)
        }
    }
}
