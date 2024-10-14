package com.example.jagree03.mediaoasis

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Spinner
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    val spinnerItems = arrayOf("Movies", "Video Games", "TV Show Sets")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupSpinner()
    }

    /**
     * This code setups the spinner by attaching an arrayAdapter
     * Code reference: https://www.youtube.com/watch?v=lAckLFH7mIE
     */
    fun setupSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinnerItemSelect)
        val arrayAdapterSpinner = ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems)
        spinner.adapter = arrayAdapterSpinner
    }


}