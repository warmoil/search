package org.joy.search.controller

import org.joy.search.model.StockModel
import org.joy.search.util.isCho
import org.joy.search.util.getChoOrChar
import org.joy.search.util.getChoOrNull
import org.joy.search.util.haveJONG
import org.joy.search.util.isSameChoAndJung
import org.joy.search.util.umJulValid
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
        // 빈칸이면 빈값
        if (search.isEmpty()) return ResponseEntity.ok(listOf())

        // 한글자 일경우
        if (search.length == 1) {
            // 한글자인데 초성일경우
            if (isCho(search.first())) return ResponseEntity.ok(stocks.filter { it.choSung.first() == search.first() }
                .take(20)
                .toList()
            ) // 한글자인데 초성이 아니고 받침이 없을경우
            else if (umJulValid(search.first()) && !haveJONG(search.first())) return ResponseEntity.ok(
                stocks.asSequence().filter {
                    isSameChoAndJung(
                        it.name.first(),
                        search.first()
                    )
                }.take(20)
                    .toList()
                    .take(20)
            )else if(umJulValid(search.first()) && haveJONG(search.first())) return  ResponseEntity.ok(
                stocks.asSequence()
                    .filter { it.name.startsWith(search) }
                    .take(20)
                    .toList()
            )

            // 한글자인데 한글이 아니면 그냥 첫번째 글자 검색
            else return ResponseEntity.ok(
                stocks.asSequence().filter { it.name.first() == search.first() }
                    .take(20)
                    .toList()
            )
        }

        // 두글자 이상일경우
        println("두글자이상 검색 :$search")
        val beforeLastStr = search.substring(0, search.length - 1)
        // 검색어보다 짧고 검색어 마지막직전 문자열로 시작 하지 않을경우
        val filterShotAndNotStart = stocks.asSequence().filter { it.name.length >= search.length }
            .filter { it.name.startsWith(beforeLastStr) }

        // 마지막 글자가 초성일경우
        if (isCho(search.last())) {
            return ResponseEntity.ok(
                filterShotAndNotStart
                    .filter {
                        val nowCho = getChoOrNull(it.name[beforeLastStr.length])
                        // 마지막 글자전 까지 같고 마지막 초성이 같아야함
                        nowCho == search.last()
                    }.take(20).toList()
            )
        }
        // 받침이 없을경우 (받침이 있는 글자도 찾음)
        return if (!haveJONG(search.last())) {
            ResponseEntity.ok(
                filterShotAndNotStart
                    .filter { isSameChoAndJung(it.name[beforeLastStr.length], search.last()) }
                    .take(20)
                    .toList()
            )
        } else {
            ResponseEntity.ok(filterShotAndNotStart
                .filter { it.name.startsWith(search) }
                .take(20)
                .toList()
            )
        }
    }
}


fun getStockList(): List<StockModel> {
    return File("종목.txt")
        .readLines().map {
            val strings = it.split("\t")
            StockModel(strings[0], strings[1], String(strings[1].map { c -> getChoOrChar(c) }.toCharArray()))
        }
}
