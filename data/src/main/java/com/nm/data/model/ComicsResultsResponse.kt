package com.nm.data.model

class ComicsResultsResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val modified: String,
    val prices : ArrayList<PricesResponse>,
    val thumbnail: ThumbnailResponse
)