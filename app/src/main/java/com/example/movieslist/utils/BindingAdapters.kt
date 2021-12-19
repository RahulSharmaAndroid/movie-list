package com.example.movieslist.moviedetails.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieslist.R


@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?) {

    url?.let {
        Glide.with(view).load(it).placeholder(R.drawable.ic_launcher_background).into(view)
    }


}