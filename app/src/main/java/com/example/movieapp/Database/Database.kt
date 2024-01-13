package com.example.movieapp.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movieapp.Adapters.DataMovie
import com.example.movieapp.fragments.Movies
import android.content.ContentValues as ContentValues

class Database(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_version), Interface {

    companion object {
        const val DB_Name = "Movie"
        const val DB_version = 1
        const val TB_name = "Movies"
        const val ID = "ID"
        const val Movies_name = "Name"
        const val Movies_author = "Author"
        const val Movies_description = "Description"
        const val Movies_date = "Date"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TB_name($ID integer primary key autoincrement,$Movies_name text not null, $Movies_author text not null,$Movies_description text ,$Movies_date text )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun addData(dataMovie: DataMovie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Movies_name, dataMovie.Movie_name)
        contentValues.put(Movies_author, dataMovie.Movie_author)
        contentValues.put(Movies_description, dataMovie.Movie_discription)
        contentValues.put(Movies_date, dataMovie.Movie_date)
        database.insert(TB_name, null, contentValues)
    }

    override fun editData(dataMovie: DataMovie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Movies_name, dataMovie.Movie_name)
        contentValues.put(Movies_author, dataMovie.Movie_author)
        contentValues.put(Movies_description, dataMovie.Movie_discription)
        contentValues.put(Movies_date, dataMovie.Movie_date)
        database.update(TB_name, contentValues, "ID=?", arrayOf(dataMovie.ID.toString()))

    }

    override fun deleteData(dataMovie: DataMovie) {
        val database = this.writableDatabase
        database.delete(TB_name, "$ID=?", arrayOf(dataMovie.ID.toString()))
    }

    override fun listData(): List<DataMovie> {
        val list = ArrayList<DataMovie>()
        val database = this.readableDatabase
        val query = "select * from $TB_name"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val author = cursor.getString(2)
                val description = cursor.getString(3)
                val date = cursor.getString(4)
                val movies = DataMovie(id, name, author, description, date)
                list.add(movies)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getDataById(id: Int): DataMovie {
        val database = this.readableDatabase
        val cursor = database.query(
            TB_name, arrayOf(ID, Movies_name, Movies_author, Movies_description, Movies_date),
            "$ID=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return DataMovie(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
    }

}