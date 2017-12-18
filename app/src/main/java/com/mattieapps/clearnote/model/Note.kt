package com.mattieapps.clearnote.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by andrewmattie on 12/17/17.
 */

@Entity(tableName = "notes")
class Note {
    @PrimaryKey(autoGenerate = true)
    var _id: Long? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "body")
    var body: String? = null

    @ColumnInfo(name = "archived")
    var archived: Boolean? = null
}
