package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */
class Saison{
      ///id
    @SerializedName("id")
    @Expose
    var id: Int? = null

      //affiche
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    //affiche
    @SerializedName("season_nb")
    @Expose
    var seasonNumber: String? = null


    ////title
    @SerializedName("title")
    @Expose
    var title: String? = null


    ////description
    @SerializedName("overview")
    @Expose
    var overview: String? = null

    ////les episodes
    @SerializedName("episodes_ids")
    @Expose
    var episodesIds: List<Int>? = null

    /////commentaires
    @SerializedName("comments_ids")
    @Expose
    var commentsIds: List<Int>? = null

    ////Evaluations
    @SerializedName("evaluations_ids")
    @Expose
    var evaluationsIds: List<Int>? = null


    @SerializedName("personnes_ids")
    @Expose
    var personnesIds: List<Int>? = null

}