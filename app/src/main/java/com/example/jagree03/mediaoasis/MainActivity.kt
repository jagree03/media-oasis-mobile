package com.example.jagree03.mediaoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
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

    fun goToRegistration(view: View) {
        val send = Intent(this, RegistrationActivity::class.java)

        startActivity(send)
    }

    fun debugGoToAnyActivity() {
        val send = Intent(this, MenuActivity::class.java)

        startActivity(send)
    }
}