package com.example.bullcookies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crudpesan.*
import java.util.*

class CRUDPesan : AppCompatActivity() {
    lateinit var dateEdt: EditText
    lateinit var timeEdt: EditText

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudpesan)

        // on below line we are initializing our variables.
        dateEdt = findViewById(R.id.enterDate)
        timeEdt = findViewById(R.id.enterTime)

        // on below line we are adding
        // click listener for our edit text.
        dateEdt.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateEdt.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        timeEdt.setOnClickListener{
            val c = Calendar.getInstance()

            // on below line we are getting our hour, minute.
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            // on below line we are initializing
            // our Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    // on below line we are setting selected
                    // time in our text view.
                    timeEdt.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.show()
        }

        addName.setOnClickListener {

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBPesan(this, null)
            // creating variables for values
            // in name and age edit texts
            val date = enterDate.text.toString()
            val time = enterTime.text.toString()
            val numTable = enterNumTable.text.toString()
            val kdMenu = enterKodeMenu.text.toString()
            val price = enterPrice.text.toString()

            // calling method to add
            // name to our database
            db.addName(date, time, numTable, kdMenu, price)

            // Toast to message on the screen
            Toast.makeText(this, date + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterDate.text.clear()
            enterTime.text.clear()
            enterNumTable.text.clear()
            enterKodeMenu.text.clear()
            enterPrice.text.clear()
        }

        printName.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBPesan(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Date.append(cursor.getString(cursor.getColumnIndex(DBPesan.DATE_COl)) + "\n")
            Time.append(cursor.getString(cursor.getColumnIndex(DBPesan.TIME_COL)) + "\n")
            NumTable.append(cursor.getString(cursor.getColumnIndex(DBPesan.NUM_TABLE_COL))+ "\n")
            KdMenu.append(cursor.getString(cursor.getColumnIndex(DBPesan.KD_MENU_COL))+ "\n")
            Price.append(cursor.getString(cursor.getColumnIndex(DBPesan.PRICE_COL))+ "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Date.append(cursor.getString(cursor.getColumnIndex(DBPesan.DATE_COl)) + "\n")
                Time.append(cursor.getString(cursor.getColumnIndex(DBPesan.TIME_COL)) + "\n")
                NumTable.append(cursor.getString(cursor.getColumnIndex(DBPesan.NUM_TABLE_COL))+ "\n")
                KdMenu.append(cursor.getString(cursor.getColumnIndex(DBPesan.KD_MENU_COL))+ "\n")
                Price.append(cursor.getString(cursor.getColumnIndex(DBPesan.PRICE_COL))+ "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}