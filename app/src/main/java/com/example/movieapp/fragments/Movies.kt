package com.example.movieapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieapp.Adapters.DataMovie
import com.example.movieapp.Adapters.Setadapter
import com.example.movieapp.Database.Database
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentEditMoviesBinding
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.databinding.ItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Movies.newInstance] factory method to
 * create an instance of this fragment.
 */
class Movies : Fragment() {
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

    private lateinit var list: ArrayList<DataMovie>
    private lateinit var database: Database
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var setadapter: Setadapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        database = Database(requireContext())
        list = ArrayList(database.listData())
        setadapter = Setadapter(list, object : Setadapter.onItemClickListener {
            override fun onDelete(dataMovie: DataMovie, position: Int) {
                database.deleteData(dataMovie)
                list.remove(dataMovie)
                setadapter.notifyItemRemoved(position)
                setadapter.notifyItemRangeChanged(position, list.size)
            }

            override fun onEdit(dataMovie: DataMovie, position: Int) {
                val bundle = Bundle()
                bundle.putInt("id", dataMovie.ID)
                val editMovies = EditMovies()
                editMovies.arguments = bundle
                findNavController().navigate(R.id.editMovies, bundle)
            }

        })

        binding.rv.setOnClickListener{
            findNavController().navigate(R.id.avangers)
        }
        class itemClickListener : ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
            }
        }
        binding.rv.adapter = setadapter
        binding.floatingbtn.setOnClickListener {
            findNavController().navigate(R.id.action_movies_to_addMovies)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Movies().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}