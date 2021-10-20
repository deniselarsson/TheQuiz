package com.deniselarsson.thequiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startButton = findViewById<Button>(R.id.btn_start)

        startButton.setOnClickListener{
                val intent = Intent(this, PlayerActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
}