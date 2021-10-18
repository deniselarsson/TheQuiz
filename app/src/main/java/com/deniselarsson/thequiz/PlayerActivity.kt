package com.deniselarsson.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val context = this
        val db = DatabaseHandler(context)

        btn_save.setOnClickListener {
            if (inputName.text.toString().isNotEmpty()){
                val user = User(inputName.text.toString())
                db.insertData(user)
            } else {
                Toast.makeText(context, "Please fill out the forms", Toast.LENGTH_SHORT).show()
            }
        }
        btn_read.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
                finish()
        }
        btn_play.setOnClickListener{
            val intent = Intent(this, QuizQuestionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}



