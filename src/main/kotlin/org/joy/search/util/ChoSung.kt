package org.joy.search.util

const val CHO_START_CODE = 44032
const val CHO_LAST_CODE = 55203
const val CHO_TERM = 588
private val choList = listOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')


fun getChoIndexOrNull(c: Char): Int? {
    return if (hangulValid(c)) {
        getChoIndex(c)
    } else null
}

fun getChoIndex(c: Char): Int {
    return ((c.code - CHO_START_CODE) / CHO_TERM)
}
// 한글 음절이면 초성으로 변경합니다
fun getChoOrChar(c: Char): Char {
    return if (umJulValid(c)) {
        choList[getChoIndex(c)]
    } else c

}

// 초성인지 확인합니다
fun choValid(c:Char): Boolean{
    return c in 'ㄱ' .. 'ㅎ'
}
// 음절 범위인지 확인합니다
fun umJulValid(c:Char): Boolean{
    return c in '가'..'힣'
}
// 한글인지 확인합니다
fun hangulValid(c: Char): Boolean {
    return choValid(c) || umJulValid(c)
}

fun getChoSungList(): List<Char> {
    return listOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')
}