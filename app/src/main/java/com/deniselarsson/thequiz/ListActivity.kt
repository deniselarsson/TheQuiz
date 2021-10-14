package com.deniselarsson.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.activity_player.btn_read

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val result = findViewById<TextView>(R.id.result)

        val context = this
        val db = DatabaseHandler(context)

        btn_list.setOnClickListener {
            var data = db.readData()
            result.text = ""
            for (i in 0 until data.size) {
                result.append(data[i].id.toString() + " " + data[i].name + "\n")
            }
        }
        btn_back.setOnClickListener{
                val intent = Intent(this, PlayerActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
}
