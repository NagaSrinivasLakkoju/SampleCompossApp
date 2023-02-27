package com.example.samplecompossapp.bottom_navigation

import com.example.samplecompossapp.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object HomeView : NavigationItem("home", R.drawable.ic_home, "Home")
    object MoviesView : NavigationItem("movies", R.drawable.ic_music, "Movies")
    object PlantsView : NavigationItem("plants", R.drawable.ic_movie, "Plants")
    object BooksView : NavigationItem("books", R.drawable.ic_book, "Books")
    object ProfileView : NavigationItem("profile", R.drawable.ic_profile, "Profile")
}
