package com.nm.data.model

class ComicsDataResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results : ArrayList<ComicsResultsResponse>
)