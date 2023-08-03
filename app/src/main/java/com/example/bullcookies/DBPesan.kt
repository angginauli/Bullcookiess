package com.example.bullcookies

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBPesan (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NM, factory, DATABASE_VERSION) {
    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_PESAN + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                DATE_COl + " TEXT," +
                TIME_COL + " TEXT," +
                NUM_TABLE_COL + " TEXT," +
                KD_MENU_COL + " TEXT," +
                PRICE_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESAN)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(date : String, time: String, numTable: String, kdMenu: String, price: String){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(DATE_COl, date)
        values.put(TIME_COL, time)
        values.put(NUM_TABLE_COL, numTable)
        values.put(KD_MENU_COL, kdMenu)
        values.put(PRICE_COL, price)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_PESAN, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_PESAN, null)

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NM = "BullCookies"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_PESAN = "pesan"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for kode column
        val DATE_COl = "date"

        // below is the variable for name column
        val TIME_COL = "time"

        // below is the variable for name column
        val NUM_TABLE_COL = "numTable"

        val KD_MENU_COL = "kdMenu"

        val PRICE_COL = "price"
    }
}