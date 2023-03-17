package org.joy.search.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "stock")
data class StockEntity(
    @Id
    val no: String,
    val name: String,
    val choSung: String,
    val jungSung: String
)