package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */
class Salle{



    @SerializedName("id")
    @Expose
    var id: Int? = null

    
    @SerializedName("name")
    @Expose
    var name: String? = null


    @SerializedName("poster")
    @Expose
    var posterPath: String? = null

    @SerializedName("movie")
    @Expose
    var movie: String? = null

    @SerializedName("movies_ids")
    @Expose
    var moviesIds: List<Int>? = null



}