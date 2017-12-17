package com.mattieapps.clearnote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        notesListView.set

        newNoteFAB.setOnClickListener {
            val intent = Intent(applicationContext, NewNoteActivity::class.java)
            applicationContext.startActivity(intent)
        }
    }
}
