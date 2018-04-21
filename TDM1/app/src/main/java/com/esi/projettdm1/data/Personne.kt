package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */


class Personne {
    /////Nom
    @SerializedName("nom")
    @Expose
    var nom: String? = null
    //////id
    @SerializedName("id")
    @Expose
    var id: Int? = null



    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null


    /////Kotlin
    @SerializedName("date_naissance")
    @Expose
    var dateNaissance: String? = null
    /////////////Biographie
    @SerializedName("biographie")
    @Expose
    var biographie: String? = null

    ////Filmographie
    @SerializedName("movies_ids")
    @Expose
    var moviesIds: List<Int>? = null
    @SerializedName("series_ids")
    @Expose
    var seriesIds: List<Int>? = null

    ////////////Commentaires
    @SerializedName("comments_ids")
    @Expose
    var commentsIds: List<Int>? = null
    ///////////Evaluations
    @SerializedName("evaluations_ids")
    @Expose
    var evaluations_ids: List<Int>? = null



}