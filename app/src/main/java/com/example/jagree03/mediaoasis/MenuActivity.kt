package com.example.jagree03.mediaoasis

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlin.time.Duration

class MenuActivity : AppCompatActivity() {

    //val spinnerItems = arrayOf("Movies", "Video Games", "TV Show Sets")

    lateinit var toggle: ActionBarDrawerToggle // toggling the slider navigation view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // setupSpinner()




        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() // telling toggle it's ready to be activated

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // able to open the toggle, when its open, that toggle menu can respond to a back arrow action of the android system wide on-screen navigation buttons

        navView.setNavigationItemSelectedListener {
            when(it.itemId) { // it refers to the current menu item that was clicked on, we want to respond to the id of that item, the id's provided in the menu resource file.
                R.id.buyMenu -> Toast.makeText(applicationContext, "Buy Menu", Toast.LENGTH_SHORT).show()
                R.id.logout -> Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()
            }
            true // return this lambda expression and return true means click event has been handled
        }
    }

    /**
     * onOptionsItemSelected must be overrided to respond correctly to clicks on the toggle
     * button and to respond to click on the menu items in the navigation drawer.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true // user clicked on that toggle button and then need to return true on this onOptionsItemSelected function
        }
        return super.onOptionsItemSelected(item)
    }

//    /**
//     * This code setups the spinner by attaching an arrayAdapter
//     * Code reference: https://www.youtube.com/watch?v=lAckLFH7mIE
//     */
//    fun setupSpinner() {
//        val spinner = findViewById<Spinner>(R.id.spinnerItemSelect)
//        val arrayAdapterSpinner = ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems)
//        spinner.adapter = arrayAdapterSpinner
//    }


}