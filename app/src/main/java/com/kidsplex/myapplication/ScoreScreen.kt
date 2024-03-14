package com.kidsplex.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ScoreScreen : AppCompatActivity() {
    private lateinit var score:TextView
    private lateinit var homescn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_screen)
        score = findViewById(R.id.scr_screen)
        homescn = findViewById(R.id.home)
        homescn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        var txt = intent.getIntExtra("score", 0)
        score.text = "Score : \n  $txt"

    }
}
