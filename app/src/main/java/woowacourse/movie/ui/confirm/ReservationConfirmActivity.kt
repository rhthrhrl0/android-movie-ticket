package woowacourse.movie.ui.confirm

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.TicketsState
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.DecimalFormatters
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_TICKETS
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count_and_seat) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val tickets = intent.getParcelableExtraCompat<TicketsState>(KEY_TICKETS)
            ?: return keyError(KEY_TICKETS)
        setInitReservationData(tickets)
    }

    private fun setInitReservationData(
        tickets: TicketsState
    ) {
        titleTextView.text = tickets.movieState.title
        dateTextView.text =
            DateTimeFormatters.convertToDateTime(tickets.dateTime)
        reservationCountTextView.text =
            getString(
                R.string.person_count_and_seat,
                tickets.tickets.size,
                tickets.tickets.map { it.seatPositionState }.joinToString { it.toString() }
            )
        setDiscountApplyMoney(tickets)
    }

    private fun setDiscountApplyMoney(tickets: TicketsState) {
        val discountApplyMoney = tickets.totalDiscountedMoneyState

        moneyTextView.text =
            DecimalFormatters.convertToMoneyFormat(discountApplyMoney)
    }
}
