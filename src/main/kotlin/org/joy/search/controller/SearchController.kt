package org.joy.search.controller

import org.joy.search.entity.StockEntity
import org.joy.search.model.StockModel
import org.joy.search.repository.StockRepository
import org.joy.search.util.getChoOrChar
import org.joy.search.util.getChoOrNull
import org.joy.search.util.getJungOrChar
import org.joy.search.util.haveJONG
import org.joy.search.util.isCho
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
class SearchController(
    private val stockRepository: StockRepository
) {
//    var stocks: List<StockModel> = getStockList()
    var entityList: List<StockEntity> = listOf()
    lateinit var lastUpdate: LocalDateTime

    @GetMapping("/2")
    fun search2(@RequestParam("search") search: String): ResponseEntity<List<StockEntity>> {
        val startTime = getNow()
        // 빈칸이면 빈값
        if (search.isEmpty()) return ResponseEntity.ok(listOf())
        val stockList = stockRepository.findAllByNameContaining(search)
        print("DB 로드 시간:${  getNow()-startTime} 갯수:${stockList.toList().size} \t")
        // 한글자 일경우
        if (search.length == 1) {
            // 한글자인데 초성일경우
            if (isCho(search.first())) {
                print("걸린시간:"+(getNow()-startTime)+"\n")
                return ResponseEntity.ok(stockList.asSequence().filter { it.choSung.first() == search.first() }
                    .take(20)
                    .toList()
                )
            } // 한글자인데 초성이 아니고 받침이 없을경우
            else if (umJulValid(search.first()) && !haveJONG(search.first())) {
                print("걸린시간:"+(getNow()-startTime)+"\n")
                return ResponseEntity.ok(
                    stockList.asSequence().filter {
                        isSameChoAndJung(
                            it.name.first(),
                            search.first()
                        )
                    }.take(20)
                        .toList()
                        .take(20)
                )
            } else if (umJulValid(search.first()) && haveJONG(search.first())) {
                print("걸린시간:"+(getNow()-startTime)+"\n")
                return ResponseEntity.ok(
                    stockList.asSequence()
                        .filter { it.name.startsWith(search) }
                        .take(20)
                        .toList()
                )
            }

            // 한글자인데 한글이 아니면 그냥 첫번째 글자 검색
            else {
                print("걸린시간:"+(getNow()-startTime)+"\n")
                return ResponseEntity.ok(
                    stockList.asSequence().filter { it.name.first() == search.first() }
                        .take(20)
                        .toList()
                )
            }
        }

        // 두글자 이상일경우
        println("두글자이상 검색 :$search")
        val choNum = search.map { if (isCho(it)) 1 else 0 }
            .sum()
        if (choNum >= 2) {
            print("걸린시간:"+(getNow()-startTime)+"\n")
            return ResponseEntity.ok(
                listOf(StockEntity(no = "초성검색은 지원하지 않습니다.", name = "초성검색은 지원하지 않습니다.", choSung = "", jungSung = ""))
            )
        }
        val beforeLastStr = search.substring(0, search.length - 1)
        // 검색어보다 짧고 검색어 마지막직전 문자열로 시작 하지 않을경우
        val filterShotAndNotStart = stockList.asSequence().filter { it.name.length >= search.length }
            .filter { it.name.startsWith(beforeLastStr) }

        // 마지막 글자가 초성일경우
        if (isCho(search.last())) {
            print("걸린시간:"+(getNow()-startTime)+"\n")
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
            print("걸린시간:"+(getNow()-startTime)+"\n")
            ResponseEntity.ok(
                filterShotAndNotStart
                    .filter { isSameChoAndJung(it.name[beforeLastStr.length], search.last()) }
                    .take(20)
                    .toList()
            )
        } else {
            print("걸린시간:"+(getNow()-startTime)+"\n")
            ResponseEntity.ok(filterShotAndNotStart
                .filter { it.name.startsWith(search) }
                .take(20)
                .toList()
            )
        }
    }

    @GetMapping
//    fun search(@RequestParam("search") search: String): ResponseEntity<List<StockModel>> {
//        val startTime = getNow()
//        // 빈칸이면 빈값
//        if (search.isEmpty()) return ResponseEntity.ok(listOf())
//        // 한글자 일경우
//        if (search.length == 1) {
//            // 한글자인데 초성일경우
//            if (isCho(search.first())) {
//                print("걸린시간:"+(getNow()-startTime)+"\n")
//                return ResponseEntity.ok(stocks.filter { it.choSung.first() == search.first() }
//                    .take(20)
//                    .toList()
//                )
//            } // 한글자인데 초성이 아니고 받침이 없을경우
//            else if (umJulValid(search.first()) && !haveJONG(search.first())) {
//                print("걸린시간:"+(getNow()-startTime)+"\n")
//                return ResponseEntity.ok(
//                    stocks.asSequence().filter {
//                        isSameChoAndJung(
//                            it.name.first(),
//                            search.first()
//                        )
//                    }.take(20)
//                        .toList()
//                        .take(20)
//                )
//            } else if (umJulValid(search.first()) && haveJONG(search.first())) {
//                print("걸린시간:"+(getNow()-startTime)+"\n")
//                return ResponseEntity.ok(
//                    stocks.asSequence()
//                        .filter { it.name.startsWith(search) }
//                        .take(20)
//                        .toList()
//                )
//            }
//
//            // 한글자인데 한글이 아니면 그냥 첫번째 글자 검색
//            else {
//                print("걸린시간:"+(getNow()-startTime)+"\n")
//                return ResponseEntity.ok(
//                    stocks.asSequence().filter { it.name.first() == search.first() }
//                        .take(20)
//                        .toList()
//                )
//            }
//        }
//
//        // 두글자 이상일경우
//        println("두글자이상 검색 :$search")
//        val choNum = search.map { if (isCho(it)) 1 else 0 }
//            .sum()
//        println("\t초성:$choNum")
//        if (choNum >= 2) {
//            print("걸린시간:"+(getNow()-startTime)+"\n")
//            return ResponseEntity.ok(
//                listOf(StockModel("", "초성검색은 지원하지 않습니다.", ""))
//            )
//        }
//        val beforeLastStr = search.substring(0, search.length - 1)
//        // 검색어보다 짧고 검색어 마지막직전 문자열로 시작 하지 않을경우
//        val filterShotAndNotStart = stocks.asSequence().filter { it.name.length >= search.length }
//            .filter { it.name.startsWith(beforeLastStr) }
//
//        // 마지막 글자가 초성일경우
//        if (isCho(search.last())) {
//            print("걸린시간:"+(getNow()-startTime)+"\n")
//            return ResponseEntity.ok(
//                filterShotAndNotStart
//                    .filter {
//                        val nowCho = getChoOrNull(it.name[beforeLastStr.length])
//                        // 마지막 글자전 까지 같고 마지막 초성이 같아야함
//                        nowCho == search.last()
//                    }.take(20).toList()
//            )
//        }
//        // 받침이 없을경우 (받침이 있는 글자도 찾음)
//        return if (!haveJONG(search.last())) {
//            print("걸린시간:"+(getNow()-startTime)+"\n")
//            ResponseEntity.ok(
//                filterShotAndNotStart
//                    .filter { isSameChoAndJung(it.name[beforeLastStr.length], search.last()) }
//                    .take(20)
//                    .toList()
//            )
//        } else {
//            print("걸린시간:"+(getNow()-startTime)+"\n")
//            ResponseEntity.ok(filterShotAndNotStart
//                .filter { it.name.startsWith(search) }
//                .take(20)
//                .toList()
//            )
//        }
//    }


    fun getStockList(): List<StockModel> {
        val list = File("종목.txt")
            .readLines().map {
                val strings = it.split("\t")
                StockModel(strings[0], strings[1], String(strings[1].map { c -> getChoOrChar(c) }.toCharArray()))
            }
        val entityList =
            list.map {
                StockEntity(
                    no = it.no,
                    name = it.name,
                    choSung = it.name.map { c -> getChoOrChar(c).code }.joinToString(""),
                    jungSung = it.name.map { c -> getJungOrChar(c) }.joinToString("")
                )
            }
        println("getStockList")
        val now = getNow()
        stockRepository.saveAll(
            entityList
        )
        println("after redis 걸린시간${getNow()-now}")
        return list
    }



    fun getNow(): Long = System.currentTimeMillis()
}

