package woowacourse.movie.domain

import com.example.domain.setter.RunningTimeSetter
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class RunningTimeSetterTest {
    @Test
    fun `평일이면 평일 상영시간대를 반환해준다`() {
        val date = LocalDate.of(2023, 4, 12)
        val runningTimeSetter = RunningTimeSetter()
        val actual = runningTimeSetter.getRunningTimes(date)
        val expected = mutableListOf<LocalTime>().apply {
            for (hour in 10 until 24 step 2) {
                add(LocalTime.of(hour, 0, 0))
            }
            add(LocalTime.of(0, 0, 0))
        }
        assertEquals(expected, actual)
    }

    @Test
    fun `토요일이면 주말 상영시간대를 반환해준다`() {
        val date = LocalDate.of(2023, 4, 15)
        val runningTimeSetter = RunningTimeSetter()
        val actual = runningTimeSetter.getRunningTimes(date)
        val expected = mutableListOf<LocalTime>().apply {
            for (hour in 9 until 24 step 2) {
                add(LocalTime.of(hour, 0, 0))
            }
        }
        assertEquals(expected, actual)
    }

    @Test
    fun `일요일이면 주말 상영시간대를 반환해준다`() {
        val date = LocalDate.of(2023, 4, 16)
        val runningTimeSetter = RunningTimeSetter()
        val actual = runningTimeSetter.getRunningTimes(date)
        val expected = mutableListOf<LocalTime>().apply {
            for (hour in 9 until 24 step 2) {
                add(LocalTime.of(hour, 0, 0))
            }
        }
        assertEquals(expected, actual)
    }
}
