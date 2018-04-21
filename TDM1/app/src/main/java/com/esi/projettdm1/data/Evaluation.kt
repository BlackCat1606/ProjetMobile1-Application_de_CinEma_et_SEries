package com.esi.projettdm1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by BlackCat on 4/19/2018.
 */
class Evaluation {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("fan_id")
    @Expose
    var fanId: Int? = null

    @SerializedName("note")
    @Expose
    var note: Int? = null

}