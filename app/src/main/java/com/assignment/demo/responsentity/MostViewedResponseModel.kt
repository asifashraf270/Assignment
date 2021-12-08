package com.assignment.demo.responsentity

data class MostViewedResponseModel(
    val copyright: String?,
    val num_results: Int?,
    val results: List<Result>?,
    val status: String?
)