package com.mattieapps.clearnote.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mattieapps.clearnote.model.Note

/**
 * Created by andrewmattie on 12/17/17.
 */
class NotesListAdapter(private val context: Context, private val notes: ArrayList<Note>): BaseAdapter() {
    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Any {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return notes[position]._id as Long
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var convertView = convertView
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
//        }
//
//        val (_, title, body) = getItem(position) as Note
//        val titleTextView = convertView!!.findViewById<TextView>(R.id.titleTextView)
//        val bodyTextView = convertView.findViewById<TextView>(R.id.bodyTextView)
//
//        titleTextView.text = title
//        bodyTextView.text = body
//
//        // returns the view for the current row
        return convertView!!
    }
}