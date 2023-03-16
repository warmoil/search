package org.joy.search

import org.joy.search.model.StockModel
import org.joy.search.util.getChoOrChar
import org.joy.search.util.getChoSungList

var stocks: Map<String, StockModel> = mapOf()

private val startChar = 44032
private val lastChar = 55203
private val choList = getChoSungList()
fun main() {
    "ㄱ에서 힣까지 초성을 확인하겠습니다 그와중에 english도 확인 가.나 특수문자*도 확잍ㄴ합니다".forEach {
        print(getChoOrChar(it))
    }
}

