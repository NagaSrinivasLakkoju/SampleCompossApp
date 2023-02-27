package com.example.samplecompossapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.samplecompossapp.R
import com.example.samplecompossapp.model.Movie
import com.example.samplecompossapp.ui.listcards.MovieCard
import com.example.samplecompossapp.ui.viewmodel.MainViewModel
import com.example.samplecompossapp.utils.LoaderDialog


@Composable
fun MoviesView(mainViewModel: MainViewModel) {
    var dialogState = remember { mutableStateOf(false) }
    val viewModel = hiltViewModel<MainViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Movies using Retrofit",
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.teal_700),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        LoaderDialog(dialogState)
        viewModel.getMovieList()
        MovieList(movieList = viewModel.movieListResponse)
        dialogState.value = true
    }
}

@Composable
fun MovieList(movieList: List<Movie>) {


    LazyColumn {
        itemsIndexed(items = movieList) { index, item ->
            MovieCard(movie = item)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MoviesViewPreview() {
    val movie = Movie(
        "Coco",
        "https://howtodoandroid.com/images/coco.jpg",
        "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
        "Latest"
    )

    MovieCard(movie = movie)
}
