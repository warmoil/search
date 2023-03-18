package org.joy.search.util

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class JungSungKtTest {

    @Test
    @DisplayName("같은 초성과 중성을 가지고 있는지 확인합니다.")
    fun isSameChoAndJungTest() {
        assertEquals(isSameChoAndJung('가', '값'), true)
        assertEquals(isSameChoAndJung('하', '핳'), true)
        assertEquals(isSameChoAndJung('무', '물'), true)
        assertEquals(isSameChoAndJung('말', '마'), true)
        assertEquals(isSameChoAndJung('뿀', '뿅'), true)
        assertEquals(isSameChoAndJung('칼', '칵'), true)
        assertEquals(isSameChoAndJung('쟝', '쟌'), true)

        assertEquals(isSameChoAndJung('가', '개'), false)
        assertEquals(isSameChoAndJung('냐', '누'), false)
        assertEquals(isSameChoAndJung('바', '마'), false)
        assertEquals(isSameChoAndJung('찰', '챌'), false)
        assertEquals(isSameChoAndJung('히', '향'), false)

        assertEquals(isSameChoAndJung('a', '개'), false)
        assertEquals(isSameChoAndJung('가', '먼'), false)
        assertEquals(isSameChoAndJung('`', '개'), false)
    }

    @Test
    @DisplayName("받침이 있는지 확인합니다")
    fun haveJONGTest() {
        assertEquals(haveJONG('각'), true)
        assertEquals(haveJONG('힣'), true)
        assertEquals(haveJONG('녹'), true)
        assertEquals(haveJONG('돕'), true)
        assertEquals(haveJONG('먋'), true)
        assertEquals(haveJONG('뺧'), true)
        assertEquals(haveJONG('작'), true)
        assertEquals(haveJONG('쏭'), true)
        assertEquals(haveJONG('뺨'), true)

        assertEquals(haveJONG('가'), false)
        assertEquals(haveJONG('느'), false)
        assertEquals(haveJONG('듀'), false)
        assertEquals(haveJONG('먀'), false)
        assertEquals(haveJONG('바'), false)
        assertEquals(haveJONG('삐'), false)
        assertEquals(haveJONG('줘'), false)
        assertEquals(haveJONG('와'), false)

        assertEquals(haveJONG('a'), false)
        assertEquals(haveJONG('z'), false)
        assertEquals(haveJONG('A'), false)
        assertEquals(haveJONG('Z'), false)
        assertEquals(haveJONG('8'), false)
        assertEquals(haveJONG('0'), false)
        assertEquals(haveJONG('%'), false)
        assertEquals(haveJONG('^'), false)
        assertEquals(haveJONG('&'), false)
        assertEquals(haveJONG(';'), false)

    }

    @Test
    @DisplayName("모음의 index를 리턴합니다 . 음절이 아닐경우 -1")
    fun getIndexOfJungTest() {

        assertEquals(getIndexOfJung('가'), 0)
        assertEquals(getIndexOfJung('개'), 1)

        assertEquals(getIndexOfJung('개'), getIndexOfJung('객'))
        assertEquals(getIndexOfJung('놃'), getIndexOfJung('노'))
        assertEquals(getIndexOfJung('찡'), getIndexOfJung('찜'))
        assertEquals(getIndexOfJung('출'), getIndexOfJung('축'))
        assertEquals(getIndexOfJung('힣'), getIndexOfJung('히'))
        assertEquals(getIndexOfJung('몹'), getIndexOfJung('목'))

        // 음절이 아닐경우 -1 을 리턴합니다
        for (alphabet in ('a'..'z') + ('A'..'Z')) {
            assertEquals(getIndexOfJung(alphabet), -1)
        }
        for (cho in ('ㄱ'..'ㅎ')) {
            assertEquals(getIndexOfJung(cho), -1)
        }
    }

    @Test
    @DisplayName("받침을 제외한 음절을 리턴합니다 음절이 아니면 그대로 리턴")
    fun getJungOrCharTest() {

        assertEquals(getJungOrChar('각'), '가')
        assertEquals(getJungOrChar('핥'), '하')
        assertEquals(getJungOrChar('힣'), '히')
        assertEquals(getJungOrChar('밥'), '바')
        assertEquals(getJungOrChar('큼'), '크')
        assertEquals(getJungOrChar('꿿'), '꿰')
        assertEquals(getJungOrChar('감'), '가')
        assertEquals(getJungOrChar('무'), '무')

        for (c in 'A'..'z') {
            assertEquals(getJungOrChar(c), c)
        }

        for (c in 'ㄱ'..'ㅎ') {
            assertEquals(getJungOrChar(c), c)
        }
    }
}