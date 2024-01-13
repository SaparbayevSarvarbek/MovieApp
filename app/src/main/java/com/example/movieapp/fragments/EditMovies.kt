package com.example.movieapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieapp.Adapters.DataMovie
import com.example.movieapp.Adapters.Setadapter
import com.example.movieapp.Database.Database
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentAddMoviesBinding
import com.example.movieapp.databinding.FragmentEditMoviesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditMovies : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var database: Database
    private lateinit var binding: FragmentEditMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMoviesBinding.inflate(inflater, container, false)
        database = Database(requireContext())
        val args = this.arguments
        val id = args?.getInt("id")
        var data = database.getDataById(id.toString().toInt())
        binding.apply {
            moviename.setText(data.Movie_name)
            movieauthor.setText(data.Movie_author)
            aboutmovie.setText(data.Movie_discription)
            moviedate.setText(data.Movie_date)
            edit.setOnClickListener {
                val name=moviename.text.toString()
                val author=movieauthor.text.toString()
                val description=aboutmovie.text.toString()
                val date=moviedate.text.toString()
                data.Movie_name=name
                data.Movie_author=author
                data.Movie_discription=description
                data.Movie_date=date
                database.editData(data)
                findNavController().navigate(R.id.movies)
            }
        }
        return binding.root
    }


    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditMovies().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}