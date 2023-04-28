package woowacourse.movie.model.mapper

import com.example.domain.model.seat.SeatPosition
import woowacourse.movie.model.SeatPositionState

fun SeatPositionState.asDomain(): SeatPosition = SeatPosition(row, column)

fun SeatPosition.asPresentation(): SeatPositionState =
    SeatPositionState(
        row.ordinal + 1,
        column.ordinal + 1
    )
