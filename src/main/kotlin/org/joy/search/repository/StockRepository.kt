package org.joy.search.repository

import org.joy.search.entity.StockEntity
import org.springframework.data.repository.CrudRepository

interface StockRepository : CrudRepository<StockEntity, String> {
    fun findAllByChoSungContaining(choSung: String): List<StockEntity>
    fun findAllByNameContaining(name: String): List<StockEntity>
}