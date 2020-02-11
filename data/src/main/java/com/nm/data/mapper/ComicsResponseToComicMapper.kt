package com.nm.data.mapper

import com.nm.data.model.ComicsResponse
import com.nm.data.model.PricesResponse
import com.nm.domain.entity.Comic
import com.nm.infrastructure.net.Mapper
import java.util.*

class ComicsResponseToComicMapper : Mapper<ComicsResponse, List<Comic>>() {
    override fun transform(comicsResponse: ComicsResponse?): List<Comic> {
        val comics = mutableListOf<Comic>()

        comicsResponse?.data?.results.let {
            it?.forEach { item ->
                comics.add(
                    Comic(
                        id = item.id,
                        title = item.title,
                        description = item.description ?: "",
                        price = highiestPrice(item.prices),
                        thumbnail = "${item.thumbnail.path}.${item.thumbnail.extension}".replace(
                            "http://",
                            "https://"
                        )
                    )
                )
            }
        }

        return comics
    }

    private fun highiestPrice(prices: ArrayList<PricesResponse>): Double {
        var price = 0.0

        prices.forEach {
            if (price < it.price) {
                price = it.price
            }
        }

        return price
    }

}