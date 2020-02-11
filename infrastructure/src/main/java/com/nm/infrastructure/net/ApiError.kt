package com.nm.infrastructure.net

open class ApiError(
    open val status: Int? = null,
    open val error: String? = null,
    open val errorMessage: String? = null
)
