package com.example.jagree03.mediaoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.jagree03.mediaoasis.model.DataBaseHelper
import com.example.jagree03.mediaoasis.model.User

class MainActivity : AppCompatActivity() {

    var loginAttempts: Int = 3
    var loginDisabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forgotPassword: TextView = findViewById(R.id.textViewForgotPassword)
        forgotPassword.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                debugGoToAnyActivity()
            }
        })
    }

    fun login(view: View) {

        val message: String?
        val userNameEditText: EditText = findViewById(R.id.editTextUsernameInput)
        val userName: String = userNameEditText.text.toString()
        val userPasswordEditText: EditText = findViewById(R.id.editTextPasswordInput)
        val userPassword: String = userPasswordEditText.text.toString()

        if (loginAttempts == 0) {
            displayMessageAlert("Login disabled")
            loginDisabled = true
        }
        else
            loginAttempts -= 1 // subtract 1 from login attempts (default is 3)

        while (loginDisabled == true) {
            userNameEditText.isVisible = false
            userPasswordEditText.isVisible = false
        }

        if (userName.isEmpty() || userPassword.isEmpty()) // if empty (null), display a notification to the user
            Toast.makeText(this,"Please enter your username and password to sign in.",Toast.LENGTH_LONG).show()
        else {
            var loginConfirmed: Boolean = false
            val myDataBase = DataBaseHelper(this)
            val result = myDataBase.getUser(
                User(-1," ", " ",
                " ", " ", userName, userPassword, 0))

            println("Database =  $result")

            if (result == -1)
                message = "User not found, please try again."
            else if (result == -2)
                message = "Error: cannot open or create the database"
            else { // any other result means the user was found in the database
                message = "Welcome, $userName"
                loginConfirmed = true
            }

            displayMessageAlert(message)

            if (loginConfirmed)
                goToMainMenu()
        }
    }

    fun goToRegistration(view: View) {
        val send = Intent(this, RegistrationActivity::class.java)

        startActivity(send)
    }

    fun goToMainMenu() {
        val send = Intent(this, MenuActivity::class.java)

        startActivity(send)
    }

    fun debugGoToAnyActivity() {
        val send = Intent(this, MenuActivity::class.java)

        startActivity(send)
    }

    fun displayMessageAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.create()
        builder.show()
    }
}