package org.joy.search.util

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class ChoSungKtTest {

    @Test
    @DisplayName("음절일경우 초성의 인덱스를 아닐경우 null")
    // ㄱ ㄲ ㄴ ㄷ ... 순서대로 초성에 대한 index 를 찾습니다
    fun getChoIndexOrNullByUmjulTest() {
        assertEquals(getChoIndexOrNullByUmjul('가'), 0)
        assertEquals(getChoIndexOrNullByUmjul('힣'), 18)
        assertEquals(getChoIndexOrNullByUmjul('깡'), 1)
        assertEquals(getChoIndexOrNullByUmjul('낣'), 2)
        assertEquals(getChoIndexOrNullByUmjul('당'), 3)
        assertEquals(getChoIndexOrNullByUmjul('떨'), 4)
        assertEquals(getChoIndexOrNullByUmjul('랄'), 5)
        assertEquals(getChoIndexOrNullByUmjul('먹'), 6)
        assertEquals(getChoIndexOrNullByUmjul('불'), 7)
        assertEquals(getChoIndexOrNullByUmjul('빫'), 8)

        assertEquals(getChoIndexOrNullByUmjul('ㄱ'), null)
        assertEquals(getChoIndexOrNullByUmjul('ㄲ'), null)
        assertEquals(getChoIndexOrNullByUmjul('a'), null)
        assertEquals(getChoIndexOrNullByUmjul('f'), null)
        assertEquals(getChoIndexOrNullByUmjul('?'), null)

    }

    @Test
    @DisplayName("음절에서 초성을 뽑습니다. 음절이 아니면 null을 반환 합니다.")
    fun getChoOrNullByUmjulTest() {
        assertEquals(getChoOrNullByUmjul('가'), 'ㄱ')
        assertEquals(getChoOrNullByUmjul('힣'), 'ㅎ')
        assertEquals(getChoOrNullByUmjul('강'), 'ㄱ')
        assertEquals(getChoOrNullByUmjul('깝'), 'ㄲ')
        assertEquals(getChoOrNullByUmjul('낪'), 'ㄴ')
        assertEquals(getChoOrNullByUmjul('쌃'), 'ㅆ')
        assertEquals(getChoOrNullByUmjul('꿻'), 'ㄲ')
        assertEquals(getChoOrNullByUmjul('끏'), 'ㄲ')
        assertEquals(getChoOrNullByUmjul('상'), 'ㅅ')
        assertEquals(getChoOrNullByUmjul('돈'), 'ㄷ')
        assertEquals(getChoOrNullByUmjul('a'), null)
        assertEquals(getChoOrNullByUmjul('ㄱ'), null)
        assertEquals(getChoOrNullByUmjul('?'), null)
        assertEquals(getChoOrNullByUmjul('w'), null)
        assertEquals(getChoOrNullByUmjul('7'), null)
    }


    @Test
    @DisplayName("한글 음절이면 초성 아니면 그단어 그대로 반환합니다.")
    fun getChoOrCharTest() {
        assertEquals(getChoOrChar('가'), 'ㄱ')
        assertEquals(getChoOrChar('힣'), 'ㅎ')
        assertEquals(getChoOrChar('낪'), 'ㄴ')
        assertEquals(getChoOrChar('깞'), 'ㄲ')
        assertEquals(getChoOrChar('끏'), 'ㄲ')
        assertEquals(getChoOrChar('똛'), 'ㄸ')
        assertEquals(getChoOrChar('향'), 'ㅎ')
        assertEquals(getChoOrChar('쇽'), 'ㅅ')

        assertEquals(getChoOrChar('ㄱ'), 'ㄱ')
        assertEquals(getChoOrChar('ㅎ'), 'ㅎ')
        assertEquals(getChoOrChar('ㅁ'), 'ㅁ')

        assertEquals(getChoOrChar('a'), 'a')
        assertEquals(getChoOrChar('w'), 'w')
        assertEquals(getChoOrChar('g'), 'g')
        assertEquals(getChoOrChar('Z'), 'Z')

        assertEquals(getChoOrChar('?'), '?')
        assertEquals(getChoOrChar('`'), '`')
        assertEquals(getChoOrChar('₩'), '₩')
    }

    @Test
    @DisplayName("현재 문자가 초성인지 확인합니다 .")
    fun isChoTest() {
        assertEquals(isCho('ㄱ'), true)
        assertEquals(isCho('ㅎ'), true)
        assertEquals(isCho('ㅆ'), true)
        assertEquals(isCho('ㄸ'), true)
        assertEquals(isCho('ㄲ'), true)
        assertEquals(isCho('ㅇ'), true)
        assertEquals(isCho('ㅁ'), true)


        assertEquals(isCho('가'), false)
        assertEquals(isCho('힣'), false)
        assertEquals(isCho('힣'), false)
        assertEquals(isCho('허'), false)
        assertEquals(isCho('오'), false)
        assertEquals(isCho('옯'), false)
        assertEquals(isCho('납'), false)
        assertEquals(isCho('쨩'), false)
        assertEquals(isCho('뽮'), false)

        assertEquals(isCho('a'), false)
        assertEquals(isCho('Z'), false)
        assertEquals(isCho('b'), false)
        assertEquals(isCho('q'), false)
        assertEquals(isCho('f'), false)
        assertEquals(isCho('h'), false)
        assertEquals(isCho('`'), false)
        assertEquals(isCho('₩'), false)
        assertEquals(isCho('&'), false)

        assertEquals(isCho('ㅗ'), false)
        assertEquals(isCho('ㅐ'), false)
        assertEquals(isCho('ㅣ'), false)

        assertEquals(isCho('ㄳ'), false)
        assertEquals(isCho('ㅄ'), false)
        assertEquals(isCho('ㅄ'), false)
        assertEquals(isCho('ㄺ'), false)
        assertEquals(isCho('ㄻ'), false)
        assertEquals(isCho('ㄵ'), false)
        assertEquals(isCho('ㄶ'), false)

    }

    @Test
    @DisplayName("초성이나 받침인 한글인지 확인합니다")
    fun isChoOrJongTest() {
        assertEquals(isChoOrJong('ㄱ'), true)
        assertEquals(isChoOrJong('ㅎ'), true)
        assertEquals(isChoOrJong('ㅆ'), true)
        assertEquals(isChoOrJong('ㄸ'), true)
        assertEquals(isChoOrJong('ㄲ'), true)
        assertEquals(isChoOrJong('ㅇ'), true)
        assertEquals(isChoOrJong('ㅁ'), true)

        assertEquals(isChoOrJong('ㄳ'), true)
        assertEquals(isChoOrJong('ㅄ'), true)
        assertEquals(isChoOrJong('ㅄ'), true)
        assertEquals(isChoOrJong('ㄺ'), true)
        assertEquals(isChoOrJong('ㄻ'), true)
        assertEquals(isChoOrJong('ㄵ'), true)
        assertEquals(isChoOrJong('ㄶ'), true)


        assertEquals(isChoOrJong('가'), false)
        assertEquals(isChoOrJong('힣'), false)
        assertEquals(isChoOrJong('허'), false)
        assertEquals(isChoOrJong('오'), false)
        assertEquals(isChoOrJong('옯'), false)
        assertEquals(isChoOrJong('납'), false)
        assertEquals(isChoOrJong('쨩'), false)
        assertEquals(isChoOrJong('뽮'), false)

        assertEquals(isChoOrJong('a'), false)
        assertEquals(isChoOrJong('Z'), false)
        assertEquals(isChoOrJong('b'), false)
        assertEquals(isChoOrJong('q'), false)
        assertEquals(isChoOrJong('f'), false)
        assertEquals(isChoOrJong('h'), false)
        assertEquals(isChoOrJong('`'), false)
        assertEquals(isChoOrJong('₩'), false)
        assertEquals(isChoOrJong('&'), false)

        assertEquals(isChoOrJong('ㅗ'), false)
        assertEquals(isChoOrJong('ㅐ'), false)
        assertEquals(isChoOrJong('ㅣ'), false)
    }

    @Test
    @DisplayName("음절인지 확인합니다")
    fun umJulValidTest() {
        assertEquals(umJulValid('가'), true)
        assertEquals(umJulValid('힣'), true)
        assertEquals(umJulValid('밥'), true)
        assertEquals(umJulValid('감'), true)
        assertEquals(umJulValid('놉'), true)
        assertEquals(umJulValid('크'), true)
        assertEquals(umJulValid('칼'), true)
        assertEquals(umJulValid('쨩'), true)
        assertEquals(umJulValid('쑹'), true)
        assertEquals(umJulValid('쑹'), true)

        assertEquals(umJulValid('ㄱ'), false)
        assertEquals(umJulValid('ㅎ'), false)
        assertEquals(umJulValid('ㅋ'), false)
        assertEquals(umJulValid('ㅃ'), false)

        assertEquals(umJulValid('a'), false)
        assertEquals(umJulValid('Z'), false)
        assertEquals(umJulValid('q'), false)
        assertEquals(umJulValid('G'), false)
        assertEquals(umJulValid('R'), false)
        assertEquals(umJulValid('k'), false)
        assertEquals(umJulValid('+'), false)
        assertEquals(umJulValid('`'), false)
        assertEquals(umJulValid('₩'), false)
        assertEquals(umJulValid('-'), false)
    }

    @Test
    @DisplayName("한글인지 확인합니다.")
    fun hangulValidTest() {
        assertEquals(hangulValid('ㄱ'),true)
        assertEquals(hangulValid('ㅎ'),true)
        assertEquals(hangulValid('가'),true)
        assertEquals(hangulValid('힣'),true)
        assertEquals(hangulValid('ㄴ'),true)
        assertEquals(hangulValid('ㅂ'),true)
        assertEquals(hangulValid('무'),true)
        assertEquals(hangulValid('야'),true)
        assertEquals(hangulValid('호'),true)
        assertEquals(hangulValid('꺕'),true)
        assertEquals(hangulValid('꿻'),true)
        assertEquals(hangulValid('쨟'),true)


        assertEquals(hangulValid('a'),false)
        assertEquals(hangulValid('z'),false)
        assertEquals(hangulValid('A'),false)
        assertEquals(hangulValid('Z'),false)
        assertEquals(hangulValid('f'),false)
        assertEquals(hangulValid('q'),false)
        assertEquals(hangulValid('g'),false)
        assertEquals(hangulValid('W'),false)
        assertEquals(hangulValid('T'),false)
        assertEquals(hangulValid('H'),false)

        assertEquals(hangulValid('`'),false)
        assertEquals(hangulValid('2'),false)
        assertEquals(hangulValid(')'),false)
        assertEquals(hangulValid('+'),false)
        assertEquals(hangulValid('_'),false)
        assertEquals(hangulValid('_'),false)

    }

    @Test
    @DisplayName("초성이 같은지 확인합니다 ")
    fun isSameChoTest() {
        assertEquals(isSameCho('가','깋'),true)
        assertEquals(isSameCho('하','힣'),true)
        assertEquals(isSameCho('넌','놀'),true)
        assertEquals(isSameCho('꺅','꼭'),true)
        assertEquals(isSameCho('총','충'),true)
        assertEquals(isSameCho('정','중'),true)

        assertEquals(isSameCho('ㄱ','갑'),true)
    }

    @Test
    @DisplayName("초성을 가져옵니다 (19개)")
    fun getChoSungListTest() {
        assertEquals(getChoSungList().size,19)
    }
}