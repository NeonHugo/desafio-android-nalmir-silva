package com.nm.data.model

class CharactersDataResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results : ArrayList<CharactersResultsResponse>
)