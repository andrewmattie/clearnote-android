package com.mattieapps.clearnote.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by andrewmattie on 12/15/17.
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(CREATE_NOTE_TABLE)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES)
        onCreate(database)
    }

    companion object {
        val TABLE_NOTES = "notes"
        val COLUMN_NOTE_ID = "_id"
        val COLUMN_NOTE_TITLE = "title"
        val COLUMN_NOTE_BODY= "body"

        private val DB_NAME = "clearnote.db"
        private val DB_VERSION = 1

        private val CREATE_NOTE_TABLE = ("create table $TABLE_NOTES ($COLUMN_NOTE_ID integer primary key autoincrement, "
                + "$COLUMN_NOTE_TITLE text not null, $COLUMN_NOTE_BODY text not null)")

        //Column arrays
        var allNoteColumns = arrayOf(DbHelper.COLUMN_NOTE_ID, DbHelper.COLUMN_NOTE_TITLE, DbHelper.COLUMN_NOTE_BODY)
        var allNoteColumnsSansId = arrayOf(DbHelper.COLUMN_NOTE_TITLE, DbHelper.COLUMN_NOTE_BODY)
    }
}
