package com.example.giorgi_kurdadze_8
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "database.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "books"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT NOT NULL)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    //aq vinaxavt wignebs
    fun saveBook(name: String) {

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
        }

        val id = db.insert(TABLE_NAME, null, values);

        db.close()
        if (id == -1L) {
            Log.e("DATABASE_CHECK", "Failed to insert data: $name")
        } else {
            Log.d("DATABASE_CHECK", "Data inserted successfully. ID: $id, Name: $name")
        }
    }

    fun getAllBooks(): List<BooksModel> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val data = mutableListOf<BooksModel>()

        while (cursor.moveToNext()) {

            val idColumnIndex = cursor.getColumnIndex(COLUMN_ID)
            val nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME)

            if (idColumnIndex >= 0 && nameColumnIndex >= 0) {
                val id = cursor.getLong(idColumnIndex)
                val name = cursor.getString(nameColumnIndex)
                val book = BooksModel(id, name)
                data.add(book)

            }
        }

        cursor.close()
        db.close()

        return data
    }
}