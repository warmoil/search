package org.joy.search

import org.joy.search.model.StockModel
import org.joy.search.util.getChoSungList
import org.joy.search.util.haveJONG
import org.joy.search.util.isSameChoAndJung

var stocks: Map<String, StockModel> = mapOf()

private val startChar = 44032
private val lastChar = 55203
private val choList = getChoSungList()
fun main() {

    println("무야호".substring(0,"무야호".length-1))
    println("받침있는지 ${haveJONG('가')}")
    println("받침있는지 ${haveJONG('갓')}")
    println("받침있는지 ${haveJONG('갃')}")
    println("받침있는지 ${haveJONG('나')}")
    println("받침있는지 ${haveJONG('납')}")
    println(isSameChoAndJung('가','까'))
    println(isSameChoAndJung('가','각'))
    println(isSameChoAndJung('가','갃'))

//    for (c in '가'..'까' ){
//        println("$c : ${(c.code-44032)/28}")
//    }
    println('가'-'개')
//    "ㄱ에서 힣까지 초성을 확인하겠습니다 그와중에 english도 확인 가.나 특수문자*도 확잍ㄴ합니다".forEach {
//        print(getChoOrChar(it))
//    }
}



