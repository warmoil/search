package org.joy.search.util


// 가의 코드
private const val CHO_START_CODE = 44032

// 힣의 코드
private const val CHO_LAST_CODE = 55203

// 초성간의 거리 (ex 가 까 의 거리는 588) = 모음 * 받침 = 588
private const val CHO_TERM = 588

// 중성간의 거리 (ex 가 개 의 거리는 28 = 받침갯수 28)
private const val JUNG_TERM = 28


/**
같은 초성과 중성을 가지고있는지 확인
 */
fun isSameChoAndJung(c1: Char, c2: Char): Boolean {
    return if ((umJulValid(c1) && umJulValid(c2))) {
        getIndexOfJung(c1) == getIndexOfJung(c2)
    } else false
}

// 받침이 있는 문자인지
fun haveJONG(c: Char): Boolean {
    return if (umJulValid(c)) {
        (c.code - CHO_START_CODE) % JUNG_TERM != 0
    } else false
}

/**
받침이 없는 모음 조합중에 '가' 부터의 거리를 구합니다 => 가 개 ...

주요 용도는 받침을 제외한 음절이 같은지 확인입니다 가 == 각 => true
 */
fun getIndexOfJung(c: Char): Int {
    return if (umJulValid(c)) {
        (c.code - CHO_START_CODE) / JUNG_TERM
    } else -1
}

/**
받침을 제외한 음절을 리턴합니다

ex) 각 -> 가 = 자음 + 모음

나머지는 그대로 반환됩니다.
 */
fun getJungOrChar(c: Char): Char {
    return if (umJulValid(c)) {
        Char((c.code - CHO_START_CODE) / JUNG_TERM * JUNG_TERM + CHO_START_CODE)
    } else c
}