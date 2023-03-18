package org.joy.search.util

private const val CHO_START_CODE = 44032
private const val CHO_LAST_CODE = 55203
private const val CHO_TERM = 588
private val choList =
    listOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')

/**
    음절에서 초성 index를 뽑습니다 . 초성이 올경우에는 null을 반환합니다.
*/
fun getChoIndexOrNullByUmjul(c: Char): Int? {
    return if (umJulValid(c)) {
        getChoIndex(c)
    } else null
}

/**
    음절에서 초성을 뽑습니다
 */
fun getChoOrNullByUmjul(c: Char): Char? {
    return if (umJulValid(c)) {
        choList[getChoIndex(c)]
    } else null
}

/**
    음절에서 초성을 가져옵니다 한글 음절이 아닐경우 원치않는 값이 나옵니다.
 */
private fun getChoIndex(c: Char): Int {
    // 한글 아니면 -1
    if (!hangulValid(c)) return -1

    return if (isCho(c)) {
        choList.indexOf(c)
    } else {
        ((c.code - CHO_START_CODE) / CHO_TERM)
    }

}

/**
    한글 음절이면 초성으로 변경합니다 음절이아니면 그대로 반환합니다.
 */
fun getChoOrChar(c: Char): Char {
    return if (umJulValid(c)) {
        choList[getChoIndex(c)]
    } else c

}

/**
 * 초성인지 확인합니다
 */
fun isCho(c: Char): Boolean {
//    return c in 'ㄱ'..'ㅎ'
    return c in choList
}

/** 초성 또는 받침인지 범위를 검색합니다.
 */
fun isChoOrJong(c: Char): Boolean {
    return c in 'ㄱ'..'ㅎ'
}

/** 음절 범위인지 확인합니다
 */
fun umJulValid(c: Char): Boolean {
    return c in '가'..'힣'
}

/** 한글인지 확인합니다
 */
fun hangulValid(c: Char): Boolean {
    return isChoOrJong(c) || umJulValid(c)
}

/** 같은 초성인지 확인합니다.
 */
fun isSameCho(c1: Char, c2: Char): Boolean {
    return if (hangulValid(c1) && hangulValid(c2)) {
        getChoIndex(c1) == getChoIndex(c2)
    } else {
        false
    }
}

/** 초성을 리스트로 반환합니다. 잘못된거있으면 warmOil 깃허브로 이슈 부탁드려요!.
 */
fun getChoSungList(): List<Char> {
    return choList
}