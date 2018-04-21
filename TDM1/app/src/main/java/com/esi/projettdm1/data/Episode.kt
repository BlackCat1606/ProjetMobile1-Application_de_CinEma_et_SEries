package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */
class Episode
{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null


    @SerializedName("title")
    @Expose
    var title: String? = null


    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("episode_number")
    @Expose
    var episodeNumber: String? = null


    @SerializedName("diffusions_ids")
    @Expose
    var diffusionsIds: List<Int>? = null

    @SerializedName("diffusions")
    @Expose
    var diffusions: List<String>? = null

    @SerializedName("comments_ids")
    @Expose
    var commentsIds: List<Int>? = null

    @SerializedName("evaluations_ids")
    @Expose
    var evaluationsIds: List<Int>? = null




}