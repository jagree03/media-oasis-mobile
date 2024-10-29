package com.example.jagree03.mediaoasis

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AdminMenuActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle // toggling the slider navigation view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_menu)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ///////////// Navigation Drawer + Toggle

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() // telling toggle it's ready to be activated

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // able to open the toggle, when its open, that toggle menu can respond to a back arrow action of the android system wide on-screen navigation buttons

        navView.setNavigationItemSelectedListener {
            when(it.itemId) { // it refers to the current menu item that was clicked on, we want to respond to the id of that item, the id's provided in the menu resource file.
                R.id.manageSaleItems -> {

                    Toast.makeText(applicationContext, "Manage sale items...", Toast.LENGTH_SHORT).show()

                    val adminManageSaleItemsFragment = Admin_ManageSaleItemsFragment()

                    supportFragmentManager.beginTransaction().apply { // begin fragment transaction operation
                        replace(R.id.frameLayout, adminManageSaleItemsFragment) // replacing the fragment in the frameLayout container of the activity with the buy menu fragment
                        addToBackStack(null) // allows you to go back with android back arrow navigation key
                        commit() // applying the replacement operation and commiting the transaction
                    }
                }

                R.id.manageCustomerOrders -> {

                }

                R.id.manageRegisteredAccounts -> {

                }

                R.id.managePaymentData -> {

                }

                R.id.manageReviews -> {

                }

                R.id.manageAdminProfile -> {

                }

                R.id.logout -> {
                    // Reference: alertDialog code by 'Aman Alam' from https://stackoverflow.com/questions/4850493/how-to-open-a-dialog-when-i-click-a-button
                    // converted from Java to Kotlin by Android Studio IDE converter
                    val builder = AlertDialog.Builder(this)
                    val intent = Intent(this, MainActivity::class.java)

                    builder.setMessage("Are you sure you want to log out?")

                    builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Toast.makeText(applicationContext, "Logging out...", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish() // kills the previous activity
                        }
                    })

                    builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            if (dialog != null) {
                                dialog.dismiss()
                            }
                        }
                    })

                    builder.create()
                    builder.show()
                }
            }
            true // return this lambda expression and return true means click event has been handled
        }
    }
}