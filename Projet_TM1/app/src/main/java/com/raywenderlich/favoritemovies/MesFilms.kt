package com.raywenderlich.favoritemovies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.raywenderlich.alltherecipes.FilmAdapter

class MesFilms : AppCompatActivity() {
    //private var mListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_films)

        // Get data to display
        val recipeList = arrayListOf<Int>(1, 2, 3, 4, 5, 6)
        // Create adapter
        val adapter = FilmAdapter(this, recipeList)

        // Create list view
        var mListView: Any
        mListView = findViewById<ListView>(R.id.film_list_view)
        mListView.setAdapter(adapter)

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedRecipe = recipeList.get(position)

            val detailIntent = Intent(this, DetailFilmMyFilm::class.java)
            detailIntent.putExtra("title", "salam")
            detailIntent.putExtra("url", "salam")
            startActivity(detailIntent)
        })
    }
}
