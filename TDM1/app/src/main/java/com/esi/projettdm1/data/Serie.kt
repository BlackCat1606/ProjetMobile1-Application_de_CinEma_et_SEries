package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */
class Serie{

    ////id
    @SerializedName("id")
    @Expose
    var id: Int? = null

    ///titre
    @SerializedName("title")
    @Expose
    var title: String? = null

    ////description
    @SerializedName("overview")
    @Expose
    var overview: String? = null

    ////affiche
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    ///bande d'annonce
    @SerializedName("video")
    @Expose
    var video: Boolean? = null

     ////Commentaires
    @SerializedName("comments_ids")
    @Expose
    var commentsIds: List<Int>? = null


    ///Evaluations
    @SerializedName("evaluations_ids")
    @Expose
    var evaluationsIds: List<Int>? = null

     ////Personnes
    @SerializedName("personnes_id")
    @Expose
    var personnesIds: List<Int>? = null
   //// Episodes


    @SerializedName("saisons_ids")
    @Expose
    var saisonsIds:List<Int>?=null

    @SerializedName("serieslies_ids")
    @Expose
    var serieslies:List<Int>?=null

    var genres: List<String>? = null

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null
}