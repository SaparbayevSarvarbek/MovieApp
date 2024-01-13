package com.example.movieapp.Database

import com.example.movieapp.Adapters.DataMovie
import com.example.movieapp.fragments.Movies

interface Interface {
    fun addData(dataMovie: DataMovie)
    fun editData(dataMovie: DataMovie)
    fun deleteData(dataMovie: DataMovie)
    fun listData():List<DataMovie>
    fun getDataById(id:Int):DataMovie
}