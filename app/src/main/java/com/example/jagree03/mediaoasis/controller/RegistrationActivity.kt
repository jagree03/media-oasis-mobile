package com.example.jagree03.mediaoasis

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jagree03.mediaoasis.model.User
import com.example.jagree03.mediaoasis.model.DataBaseHelper

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * This method handles showing and hiding action of the edit text of textPassword type.
     * The transformation methods used help perform this action
     *
     * Reference: https://www.scribd.com/document/716398701/Android-Show-Hide-Password-in-Edittext
     */
    fun showHidePassword(view: View) {
        val editTextPassword: EditText = findViewById(R.id.editTextPasswordInput)
        val showHidePasswordButton: Button = findViewById(R.id.button_ShowHidePassword)

        if (showHidePasswordButton.text.equals(getString(R.string.show))) {
            editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            showHidePasswordButton.setText(getString(R.string.hide))
        } else if (showHidePasswordButton.text.equals(getString(R.string.hide))) {
            editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            showHidePasswordButton.setText(getString(R.string.show))
        }
    }

    fun validateInformation(view: View) {
        val firstName: EditText = findViewById(R.id.editTextFirstNameInput)
        val lastName: EditText = findViewById(R.id.editTextLastNameInput)
        val email: EditText = findViewById(R.id.editTextEmailInput)
        val phoneNo: EditText = findViewById(R.id.editTextPhoneNumInput)
        val username: EditText = findViewById(R.id.editTextUsernameInput)
        val password: EditText = findViewById(R.id.editTextPasswordInput)
        var error: String = ""

        if (firstName.text.isEmpty()) {
            error += "First Name is Empty"
        } else if (lastName.text.isEmpty()) {
            error += "Last Name is Empty"
        } else if (email.text.isEmpty()) {
            error += "Email Address is Empty"
        } else if (!email.text.contains('@')) {
            error += "Email Address missing @ character"
        } else if (phoneNo.text.isEmpty()) {
            error += "Phone Number is Empty"
        } else if (username.text.isEmpty()) {
            error += "Username is Empty"
        } else if (password.text.isEmpty()) {
            error += "Password is Empty"
        }

        error += validatePasswordStrength(password.text.toString())

        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
        //return error
    }

    fun validatePasswordStrength(password: String): String {
        var error: String = ""

        return error
    }

    fun registerNewUser(view: View) {

        val firstName: String = findViewById<EditText>(R.id.editTextFirstNameInput).text.toString()
        val lastName: String = findViewById<EditText>(R.id.editTextLastNameInput).text.toString()
        val email: String = findViewById<EditText>(R.id.editTextEmailInput).text.toString()
        val phoneNo: String = findViewById<EditText>(R.id.editTextPhoneNumInput).text.toString()
        val userName: String = findViewById<EditText>(R.id.editTextUsernameInput).text.toString()
        val userPassword: String = findViewById<EditText>(R.id.editTextPasswordInput).text.toString()
        val message: String

        if(firstName.isEmpty() || lastName.isEmpty() )
            message = "Please input your first name and last name."
        else if(userName.isEmpty() || userPassword.isEmpty() )
            message = "Please input your username and password."
        else { // Process of saving/inserting the data to the database.

            // User object creation process using the User data class in the model
            val newUser = User(-1,firstName, lastName, email, phoneNo, userName, userPassword, 0)
            val myDatabase = DataBaseHelper(this)
            val result = myDatabase.addUser(newUser)

            when(result) {
                -1 -> message = "An error occurred when creating the user account."
                -2 -> message = "Error: cannot open or create the database"
                -3 -> message = "A user account with this username already exists."
                else ->  {
                    message = "Your account has been registered, thank you."
                }
            }
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }
    }
}