package com.example.jagree03.mediaoasis

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_settings)
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
}