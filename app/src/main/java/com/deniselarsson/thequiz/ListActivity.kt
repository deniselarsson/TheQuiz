package com.deniselarsson.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val context = this
        val db = DatabaseHandler(context)

        btn_list.setOnClickListener {
            var data = db.readData()
            result.text = ""
            for (i in 0 until data.size) {
                result.append(data[i].id.toString() + " " + data[i].name + "\n")
            }
        }

        btn_back.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_delete.setOnClickListener {
            if (deleteId.text.toString().isNotEmpty()) {
                db.deleteData(deleteId.text.toString().toInt())
                btn_list.performClick()
            } else {
                Toast.makeText(context, "Fill a number to delete.", Toast.LENGTH_SHORT).show()
            }
        }
        btn_start.setOnClickListener{
            val intent = Intent(this, QuizQuestionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
