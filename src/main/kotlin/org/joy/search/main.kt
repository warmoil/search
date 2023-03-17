package org.joy.search

import org.joy.search.model.StockModel
import org.joy.search.util.getChoOrChar
import org.joy.search.util.getChoSungList
import org.joy.search.util.getJungOrChar

var stocks: Map<String, StockModel> = mapOf()

private val startChar = 44032
private val lastChar = 55203
private val choList = getChoSungList()
fun main() {
    val hi = "한글 테스트입니다. 이번에는 중성 테스트를 해보겠습니다".map { getJungOrChar(it) }.joinToString("")
    println(hi)

}



