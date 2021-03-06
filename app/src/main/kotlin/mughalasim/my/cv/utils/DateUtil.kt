package mughalasim.my.cv.utils

import android.content.Context
import mughalasim.my.cv.R
import mughalasim.my.cv.utils.DateTimeExtensions.dateFormatDayFullMonthYear
import org.joda.time.DateTime
import org.joda.time.Months
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

fun DateTime.toMonthYearString(): String {
    return this.toString(dateFormatDayFullMonthYear)
}

fun DateTime.toMonthYearDuration(context: Context, dateTime: DateTime): String {
    val years = Years.yearsBetween(dateTime.withTime(0, 0, 0, 0), this.withTime(0, 0, 0, 0)).years
    val months = Months.monthsBetween(dateTime.withTime(0, 0, 0, 0), this.withTime(0, 0, 0, 0)).months % 12

    return (
           (if (years > 0) String.format(context.resources.getQuantityString(R.plurals.plural_year, years), years) else "") +
           (if (years > 0 && months > 0) " " else "") +
           if (months > 0) String.format(context.resources.getQuantityString(R.plurals.plural_month, months), months) else ""
           )
}

object DateTimeExtensions {
    const val FULL_MONTH_YEAR = "MMM yyyy"
    const val DATE_FORMAT = "yyyy-MM-dd"

    val dateFormatDayFullMonthYear: DateTimeFormatter = DateTimeFormat.forPattern(FULL_MONTH_YEAR)
    val dateFormat: DateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT)
}
