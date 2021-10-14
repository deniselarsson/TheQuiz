package com.deniselarsson.thequiz

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "MyQuiz"
const val TABLE_NAME = "Users"
const val COL_NAME = "name"
const val COL_ID = "id"

// Cursor factory = if not using pass NULL
class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        // No DB - Then create
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NAME INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user : User) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, user.name)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == (0).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("Range")
    fun readData() : MutableList<User> {
        val list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
}
