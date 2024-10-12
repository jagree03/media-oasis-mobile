package com.example.jagree03.mediaoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToRegistration(view: View) {
        val send = Intent(this, RegistrationActivity::class.java)

        startActivity(send)
    }
}