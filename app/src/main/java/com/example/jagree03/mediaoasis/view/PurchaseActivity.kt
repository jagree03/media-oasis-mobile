package com.example.jagree03.mediaoasis

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PurchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_purchase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ShoppingCartFragment = ShoppingCartFragment()

        supportFragmentManager.beginTransaction().apply { // begin fragment transaction operation
            replace(R.id.frameLayout, ShoppingCartFragment)
            addToBackStack(null) // allows you to go back with android back arrow navigation key
            commit() // applying the replacement operation and commiting the transaction
        }
    }
}