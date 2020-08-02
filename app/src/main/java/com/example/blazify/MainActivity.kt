package com.example.blazify


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun mainButton(view: View) {
        val diceNumber: Number = (1..6).random()
        val Text: TextView = findViewById(R.id.diceRoll)
        Text.text = diceNumber.toString()
    }
}

