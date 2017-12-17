package com.mattieapps.clearnote.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.sql.SQLException

/**
 * Created by andrewmattie on 1/5/16.
 * Updated on 12/16/17
 */

class Mango(private val dbHelper: SQLiteOpenHelper) {

    private var database: SQLiteDatabase? = null

    /**
     * Open Database
     *
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun open() {
        database = dbHelper.writableDatabase
    }

    /**
     * Close Database
     */
    fun close() {
        dbHelper.close()
    }

    /**
     * Save object
     *
     * @param columns   String array of columns(excluding id)
     * @param params    Object list of data
     * @param tableName Table name
     * @return Cursor object
     */
    @Throws(IllegalArgumentException::class)
    fun save(columns: Array<String>, params: List<Any>, tableName: String): Cursor {
        val count = params.size

        val contentValues = ContentValues()

        for (i in 0 until count) {
            val paramObject = params[i]
            when (paramObject) {
                is String -> contentValues.put(columns[i], params[i] as String)
                is Double -> contentValues.put(columns[i], params[i] as Double)
                is Int -> contentValues.put(columns[i], params[i] as Int)
                is Boolean -> contentValues.put(columns[i], params[i] as Boolean)
                is Long -> contentValues.put(columns[i], params[i] as Long)
                is Float -> contentValues.put(columns[i], params[i] as Float)
                is Short -> contentValues.put(columns[i], params[i] as Short)
                is Byte -> contentValues.put(columns[i], params[i] as Byte)
                else -> throw IllegalArgumentException()
            }
        }

        val insertId = database!!.insert(tableName, null, contentValues)
        val cursor = database!!.query(tableName, columns, columns[0] + " = "
                + insertId, null, null, null, null)
        cursor.moveToFirst()

        //        cursorToObject(model, cursor);

        return cursor
    }

    /**
     * Delete object
     *
     * @param tableName Table name
     * @param idColumn  Column id
     * @param id        Object id
     */
    fun delete(tableName: String, idColumn: String, id: Int) {
        database!!.delete(tableName, idColumn + " = " + id, null)
    }

    /**
     * Edit object
     *
     * @param columns   String array of columns(excluding id)
     * @param params    Object list of params
     * @param tableName Table name
     * @param idColumn  id of target column
     * @param id        id of item
     */
    fun edit(columns: Array<String>, params: List<Any>, tableName: String, idColumn: String, id: Long) {
        val count = params.size

        val contentValues = ContentValues()

        for (i in 0 until count) {
            //            contentValues.put(columns[i + 1], params[i]);
            val paramObject = params[i]
            if (paramObject is String) {
                contentValues.put(columns[i], params[i] as String)
            } else if (paramObject is Double) {
                contentValues.put(columns[i], params[i] as Double)
            } else if (paramObject is Int) {
                contentValues.put(columns[i], params[i] as Int)
            } else if (paramObject is Boolean) {
                contentValues.put(columns[i], params[i] as Boolean)
            } else if (paramObject is Long) {
                contentValues.put(columns[i], params[i] as Long)
            } else if (paramObject is Float) {
                contentValues.put(columns[i], params[i] as Float)
            } else if (paramObject is Short) {
                contentValues.put(columns[i], params[i] as Short)
            } else if (paramObject is Byte) {
                contentValues.put(columns[i], params[i] as Byte)
            } else {
                throw IllegalArgumentException()
            }
        }

        database!!.update(tableName, contentValues, idColumn + " = " + id, null)
    }

    /**
     * Retrieve all objects
     *
     * @param tableName Table name
     * @param columns   String array of columns
     * @return Cursor object
     */
    fun fetchAllFromObject(tableName: String, columns: Array<String>): Cursor? {
        val cursor = database!!.query(tableName, columns, null, null, null, null, null)

        cursor?.moveToFirst()

        return cursor
    }

    /**
     * Find object by id
     *
     * @param tableName Table name
     * @param params    Columns to select
     * @param idColumn  Id of column
     * @param id        Object Id
     * @return Cursor object
     */
    fun findById(tableName: String, params: Array<String>, idColumn: String, id: Long): Cursor? {
        var queryString = "SELECT "

        for (i in params.indices) {
            if (i < params.size - 1) {
                queryString += params[i] + ", "
            } else {
                queryString += params[i] + " "
            }
        }

        queryString += "FROM $tableName WHERE $idColumn = ?"

        val cursor = database!!.rawQuery(queryString, arrayOf(id.toString()))

        cursor?.moveToFirst()

        return cursor
    }

    /**
     * Find one object from position
     *
     * @param tableName Table name
     * @param columns   String array of columns
     * @param position  Known position of object
     * @return Cursor object
     */
    fun fetchOneFromPosition(tableName: String, columns: Array<String>, position: String): Cursor? {
        val cursor = database!!.query(tableName, columns, null, null, null, null, null)

        cursor?.moveToPosition(Integer.valueOf(position)!!)

        return cursor
    }

    /**
     * Run a custom query
     *
     * @param query String of the query
     * @param params String array of paramaters
     * @param position Known position of object(optional)
     */
    fun customQuery(query: String, params: Array<String>, position: String?): Cursor? {
        val cursor = database!!.rawQuery(query, params)

        if (cursor != null) {
            if (position != null) {
                cursor.moveToPosition(Integer.valueOf(position)!!)
            } else {
                cursor.moveToFirst()
            }
        }

        return cursor
    }
}
