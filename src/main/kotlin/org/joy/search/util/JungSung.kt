package org.joy.search.util


// 가의 코드
private const val CHO_START_CODE = 44032
// 힣의 코드
private const val CHO_LAST_CODE = 55203

// 초성간의 거리 (ex 가 까 의 거리는 588) = 모음 * 받침 = 588
private const val CHO_TERM = 588

// 중성간의 거리 (ex 가 개 의 거리는 28 = 받침갯수 28)
private const val JUNG_TERM = 28


// 같은 중성을 가지고있는지
fun isSameChoAndJung(c1: Char, c2: Char): Boolean {
    return getIndexOfJung(c1) == getIndexOfJung(c2)
}

// 받침이 있는 문자인지
fun haveJONG(c: Char): Boolean {
    return (c.code - CHO_START_CODE) % JUNG_TERM != 0
}

// 모음의 index  ㅏ ㅐ ㅜ ㅑ 등...
fun getIndexOfJung(c: Char): Int {
    return (c.code - CHO_START_CODE) / JUNG_TERM
}
