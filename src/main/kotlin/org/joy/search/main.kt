package org.joy.search

import org.joy.search.model.StockModel
import java.io.File

var stocks: Map<String, StockModel> = mapOf()

fun main() {
    if (stocks.isEmpty()) {
        val file = File("종목.txt")
        stocks = file.readLines().map {
            val strings = it.split("\t")
            StockModel(strings[0].toLong(),strings[1],strings[2])
        }.associateBy({ it.name }, { it })
    }
    stocks.entries
        .filter { it.key.contains("정") }.forEach(::println)
}
