package com.example.bullcookies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class GridView : AppCompatActivity() {

    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModal>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModal("Menu Makan & Minum", R.drawable.menu)
        courseList = courseList + GridViewModal("Pesan", R.drawable.pesan)
        courseList = courseList + GridViewModal("Tentang Developer", R.drawable.tentang)
        courseList = courseList + GridViewModal("Lokasi", R.drawable.lokasi)
        courseList = courseList + GridViewModal("Berita", R.drawable.berita)
        courseList = courseList + GridViewModal("Tentang PPKD", R.drawable.ppkd)

        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = GridRVAdapter(courseList = courseList, this@GridView)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                applicationContext, courseList[position].courseName + " selected",
                Toast.LENGTH_SHORT
            ).show()

            if (courseList[position].courseName == "Menu Makan & Minum") {
                val intent = Intent(this, CRUDMenu::class.java)
                startActivity(intent)
            }
            else if (courseList[position].courseName == "Pesan") {
                val intent = Intent(this, CRUDPesan::class.java)
                startActivity(intent)
            }
            else if (courseList[position].courseName == "Tentang Developer") {
                val intent = Intent(this, TentangDeveloper::class.java)
                startActivity(intent)
            }
            else if (courseList[position].courseName == "Tentang PPKD") {
                val intent = Intent(this, PPKD::class.java)
                startActivity(intent)
            }
            else if (courseList[position].courseName == "Berita") {
                val intent = Intent(this, Berita::class.java)
                startActivity(intent)
            }
            else if (courseList[position].courseName == "Lokasi") {
                val intent = Intent(this, Lokasi::class.java)
                startActivity(intent)
            }
        }
    }
}