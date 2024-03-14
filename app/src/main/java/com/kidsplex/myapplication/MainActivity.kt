package com.kidsplex.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.EdgeToEdgeUtils

class MainActivity : AppCompatActivity() {
    private lateinit var start_btn:ExtendedFloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start_btn = findViewById(R.id.strt)
        start_btn.setOnClickListener{
            val intent = Intent(this,QuizScreen::class.java)
            startActivity(intent)
        }

    }

}