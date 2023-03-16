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
    var stocks: List<StockModel> = getStockList()
    lateinit var lastUpdate: LocalDateTime

    @GetMapping
    fun search(@RequestParam("search") search: String): ResponseEntity<List<StockModel>> {
        if (search.isEmpty() || search.length < 2) return ResponseEntity.ok(listOf())

        search.forEach {
            if (it in 'ㄱ'..'ㅎ') {
                return ResponseEntity.ok(listOf(StockModel("그런거", "없습니다", "ㅋㅋ")))
            }
        }
        val result = stocks
            .filter { it.name.contains(search) }.take(20)
        println("검색어:$search")
        println("갯수:${result.size}")
        return ResponseEntity.ok(
            result
        )
    }
}


fun getStockList(): List<StockModel> {
    return File("종목.txt")
        .readLines().map {
            val strings = it.split("\t")
            StockModel(strings[0], strings[1], strings[2])
        }
}
