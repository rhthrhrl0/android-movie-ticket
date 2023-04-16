package com.example.domain

import com.example.domain.discountPolicy.DefaultDiscountPolicy
import com.example.domain.model.Count
import com.example.domain.model.Money
import com.example.domain.model.Movie
import com.example.domain.model.Reservation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DefaultDiscountPolicyTest {
    @Test
    fun `무비데이면 10% 할인을 받는다`() {
        val count = Count(2)
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(12, 0)
        val dateTime = LocalDateTime.of(date, time)
        val reservation = Reservation(mockMovie, dateTime, count)

        val discountCalculator = DefaultDiscountPolicy()
        val actual = discountCalculator.discount(reservation)
        val expected = Money(23400)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 시간대가 조조면 2000원 할인을 받는다`() {
        val count = Count(2)
        val date = LocalDate.of(2023, 4, 12)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DefaultDiscountPolicy()
        val reservation = Reservation(mockMovie, dateTime, count)

        val actual = discountCalculator.discount(reservation)
        val expected = Money(22000)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 시간대가 야간이면 2000원 할인을 받는다`() {
        val count = Count(3)
        val date = LocalDate.of(2023, 4, 12)
        val time = LocalTime.of(22, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DefaultDiscountPolicy()
        val reservation = Reservation(mockMovie, dateTime, count)

        val actual = discountCalculator.discount(reservation)
        val expected = Money(33000)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비데이이고 조조면 10%를 할인 받고 추가로 2000원 할인을 더 받는다`() {
        val count = Count(2)
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DefaultDiscountPolicy()
        val reservation = Reservation(mockMovie, dateTime, count)

        val actual = discountCalculator.discount(reservation)
        val expected = Money(19400)

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        private val mockMovie = Movie(
            0,
            "title",
            LocalDate.now(),
            LocalDate.now(),
            120,
            ""
        )
    }
}
