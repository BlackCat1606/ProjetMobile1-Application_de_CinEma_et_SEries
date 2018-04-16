/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.favoritemovies

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object MovieHelper {

  val KEY_TITLE = "title"
  val KEY_RATING = "rating"
  val KEY_POSTER_URI = "posterUri"
  val KEY_OVERVIEW = "overview"

  fun getMoviesFromJson(): ArrayList<String> {

    //val movies = ArrayList<Movie>()
    val movies = arrayListOf("Acceuil","Cinéma","Série","Personne","Commentaires","Fan")

    /*try {
      // Load the JSONArray from the file
      //val jsonString = loadJsonFromFile(fileName, context)
      //val json = JSONObject(jsonString)
      //val jsonMovies = json.getJSONArray("movies")

      // Create the list of Movies
      //for (index in 0 until m.length()) {
        //val movieTitle = jsonMovies.getJSONObject(index).getString(KEY_TITLE)
        //val movieRating = jsonMovies.getJSONObject(index).getInt(KEY_RATING)
        //val moviePosterUri = jsonMovies.getJSONObject(index).getString(KEY_POSTER_URI)
        //val movieOverview = jsonMovies.getJSONObject(index).getString(KEY_OVERVIEW)
        //movies.add(Movie(movieTitle, movieRating, moviePosterUri, movieOverview))
      //}
    } catch (e: JSONException) {
      return movies
    }*/

    return movies
  }

  /*private fun loadJsonFromFile(filename: String, context: Context): String {
    var json = ""

    try {
      val input = context.assets.open(filename)
      val size = input.available()
      val buffer = ByteArray(size)
      input.read(buffer)
      input.close()
      json = buffer.toString(Charsets.UTF_8)
    } catch (e: IOException) {
      e.printStackTrace()
    }

    return json
  }*/
}