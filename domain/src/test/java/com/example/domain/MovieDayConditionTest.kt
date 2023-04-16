package com.example.domain

import com.example.domain.discountPolicy.condition.MovieDayCondition
import com.example.domain.model.Count
import com.example.domain.model.Movie
import com.example.domain.model.Reservation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDayConditionTest {
    @Test
    fun `무비 데이이면 할인을 받을 수 있다`() {
        val count = Count(2)
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(16, 0)
        val dateTime = LocalDateTime.of(date, time)
        val mockMovie = Movie(
            0,
            "title",
            LocalDate.now(),
            LocalDate.now(),
            120,
            ""
        )
        val reservation = Reservation(mockMovie, dateTime, count)

        val movieDayCondition = MovieDayCondition()
        val actual = movieDayCondition.isDiscountable(reservation)
        assertThat(actual).isTrue
    }
}
