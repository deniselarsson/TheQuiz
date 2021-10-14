package com.deniselarsson.thequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val result = findViewById<TextView>(R.id.result)

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
            var data = db.readData()
            result.text = ""
            for (i in 0 until data.size) {
                result.append(data[i].id.toString() + " " + data[i].name + "\n")
            }
        }
    }
}

