package com.example.movieslist.moviedetails

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.movieslist.moviedetails.data.moviedetails.MovieDetails
import com.example.movieslist.moviedetails.remote.MovieInterface

import com.example.movieslist.moviedetails.ui.movie.MoviePaging
import com.example.movieslist.moviedetails.utils.Events
import com.example.movieslist.moviedetails.utils.Result
import com.example.movieslist.moviedetails.utils.Status
import com.example.movieslist.moviedetails.ui.details.MovieDetailsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: MovieInterface,
    private val repository: MovieDetailsRepository
) : ViewModel() {

  val list =   Pager(PagingConfig(pageSize = 10)) {
        MoviePaging(movieInterface)
    }.liveData.cachedIn(viewModelScope)


    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails


    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))

    }



}