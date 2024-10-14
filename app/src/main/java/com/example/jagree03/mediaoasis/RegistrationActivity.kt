package com.example.jagree03.mediaoasis

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val showHidePasswordButton: Button = findViewById(R.id.button_showhidepassword)

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

        //return error
    }

    fun validatePasswordStrength(password: String): String {
        var error: String = ""

        return error
    }

    fun debugGoToMenu(view: View) {
        val send = Intent(this, MenuActivity::class.java)

        startActivity(send)

    }
}