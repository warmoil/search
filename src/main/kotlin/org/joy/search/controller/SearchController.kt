package org.joy.search.controller

import org.joy.search.model.StockModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.time.LocalDateTime

@RestController
@RequestMapping("/stock")
class SearchController {
    var stocks: List<StockModel> = listOf()
    lateinit var lastUpdate: LocalDateTime
    @GetMapping()
    fun search(@RequestParam("search") search: String): ResponseEntity<List<StockModel>> {
        if(search.isEmpty()) return ResponseEntity.ok(listOf())
        if (stocks.isEmpty()) {
            stocks = File("종목.txt")
                .readLines().map {
                    val strings = it.split("\t")
                    StockModel(strings[0].toLong(), strings[1], strings[2])
                }
            lastUpdate = LocalDateTime.now()
        }

        val result = stocks
                .filter { it.name.contains(search) }.take(20)
        println("검색어:$search")
        println("갯수:${result.size}")
        return ResponseEntity.ok(result
        )
    }
}