package com.example.movieapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemBinding

class Setadapter(val list: List<DataMovie>,val listener:onItemClickListener) : RecyclerView.Adapter<Setadapter.Vh>() {

    inner class Vh(val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(dataMovie: DataMovie,position: Int) {
            itemBinding.apply {
                 moviename.text=dataMovie.Movie_name
                 moviedate.text=dataMovie.Movie_date
                 moviedesciption.text=dataMovie.Movie_discription
                btndelete.setOnClickListener { listener.onDelete(dataMovie,position) }
                btnedit.setOnClickListener { listener.onEdit(dataMovie,position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface onItemClickListener{
        fun onDelete(dataMovie: DataMovie,position: Int)
        fun onEdit(dataMovie: DataMovie,position: Int)
    }
}