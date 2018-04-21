package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */
class Fan {




    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("fav_movies_ids")
    @Expose
    var favMoviesIds: List<Int>? = null

    @SerializedName("fav_series_ids")
    @Expose
    var favSeriesIds: List<Int>? = null


}