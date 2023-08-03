package com.example.bullcookies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_crudmenu.*

class CRUDMenu : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudmenu)

        // below code is to add on click
        // listener to our add name button
        addName.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBMenu(this, null)

            // creating variables for values
            // in name and age edit texts
            val kode = enterKd.text.toString()
            val name = enterName.text.toString()
            val price = enterPrice.text.toString()

            // calling method to add
            // name to our database
            db.addName(kode, name, price)

            // Toast to message on the screen
            Toast.makeText(this, kode + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterKd.text.clear()
            enterName.text.clear()
            enterPrice.text.clear()
        }

        // below code is to add on  click
        // listener to our print name button
        printName.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBMenu(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Kode.append(cursor.getString(cursor.getColumnIndex(DBMenu.KODE_COl)) + "\n")
            Name.append(cursor.getString(cursor.getColumnIndex(DBMenu.NAME_COL)) + "\n")
            Price.append(cursor.getString(cursor.getColumnIndex(DBMenu.PRICE_COL))+ "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Kode.append(cursor.getString(cursor.getColumnIndex(DBMenu.KODE_COl)) + "\n")
                Name.append(cursor.getString(cursor.getColumnIndex(DBMenu.NAME_COL)) + "\n")
                Price.append(cursor.getString(cursor.getColumnIndex(DBMenu.PRICE_COL))+ "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}