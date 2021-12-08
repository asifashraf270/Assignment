package com.assignment.demo.responsentity

import com.google.gson.annotations.SerializedName

data class Media(
    val approved_for_syndication: Int?,
    val caption: String?,
    val copyright: String?,
    @SerializedName("media-metadata")
    val mediadata: List<MediaMetadata>?,
    val subtype: String?,
    val type: String?
)