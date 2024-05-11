package mughalasim.my.cv.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.b3nedikt.restring.Restring
import mughalasim.my.cv.R
import mughalasim.my.cv.services.Route
import mughalasim.my.cv.ui.utils.DateConstants.formatMonthYear
import mughalasim.my.cv.ui.utils.DateConstants.formatYearMonthDay
import org.joda.time.DateTime
import org.joda.time.Months
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

fun String.toMonthYearString(): String {
    val date = DateTime.parse(this, formatYearMonthDay)
    return date.toString(formatMonthYear)
}

fun String.toYearMonthDayDate(): DateTime {
    return DateTime.parse(this, formatYearMonthDay)
}

fun String.toRoute(): Route =
    Route::class.sealedSubclasses
        .firstOrNull { it.objectInstance?.routeName == this }
        ?.objectInstance
        ?: Route.ListScreen

@Composable
fun String.toYearMonthDuration(endDateString: String): String {
    val context = LocalContext.current.resources
    val startDate = this.toYearMonthDayDate()
    val endDate = endDateString.toYearMonthDayDate()

    val years = Years.yearsBetween(startDate.withTime(0, 0, 0, 0), endDate.withTime(0, 0, 0, 0)).years
    val months = Months.monthsBetween(startDate.withTime(0, 0, 0, 0),
        endDate.withTime(0, 0, 0, 0)).months % DateConstants.MONTHS
    return (
        (if (years > 0) String.format(context.getQuantityString(R.plurals.plural_year, years), years) else "") +
            (if (years > 0 && months > 0) " " else "") +
            if (months > 0) String.format(context.getQuantityString(R.plurals.plural_month, months), months) else ""
    )
}

@Composable
fun DateTime.toYearMonthDuration(startDateString: String): String {
    val context = LocalContext.current.resources
    val startDate = startDateString.toYearMonthDayDate()
    val endDate = this

    val years = Years.yearsBetween(startDate.withTime(0, 0, 0, 0), endDate.withTime(0, 0, 0, 0)).years
    val months = Months.monthsBetween(startDate.withTime(0, 0, 0, 0),
        endDate.withTime(0, 0, 0, 0)).months % DateConstants.MONTHS
    return (
        (if (years > 0) String.format(context.getQuantityString(R.plurals.plural_year, years), years) else "") +
            (if (years > 0 && months > 0) " " else "") +
            if (months > 0) String.format(context.getQuantityString(R.plurals.plural_month, months), months) else ""
    )
}

object DateConstants {
    val formatYearMonthDay: DateTimeFormatter =
        DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(
            Restring.locale,
        )
    val formatMonthYear: DateTimeFormatter = DateTimeFormat.forPattern("MMM yyyy").withLocale(Restring.locale)
    const val MONTHS = 12
}
