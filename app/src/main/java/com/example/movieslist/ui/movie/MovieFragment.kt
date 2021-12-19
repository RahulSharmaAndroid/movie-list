package com.example.movieslist.moviedetails.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieslist.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.movieslist.moviedetails.MovieViewModel
import com.example.movieslist.moviedetails.ui.movie.MovieFragmentDirections
import org.imaginativeworld.whynotimagecarousel.CarouselItem


@AndroidEntryPoint
class MovieFragment : Fragment() {
    lateinit var binding: FragmentMovieBinding

    val viewModel: MovieViewModel by viewModels()


    val movieAdapter = MoviePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieBinding.inflate(inflater, container, false)

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val list = mutableListOf<CarouselItem>()

        list.add(
                CarouselItem(
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg",
                        caption = "Photo by Aaron Wu on Unsplash"
                )
        )
        list.add(
                CarouselItem(
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTQyNDkzODAzMV5BMl5BanBnXkFtZTgwNDU3OTQyMTE@._V1_SX300.jpg",
                        caption = "Photo by Johannes Plenio on Unsplash"
                )
        )
        list.add(
                CarouselItem(
                        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg",
                        caption = "Photo by Johannes Plenio on Unsplash"
                )
        )

        binding.carousel.addData(list)
        setRecyclerView()



        movieAdapter.onMovieClick {
            val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        viewModel.list.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }


    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }


}