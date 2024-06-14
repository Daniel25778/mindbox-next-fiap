package br.com.mindbox.data.onboarding

import android.icu.util.Calendar
import br.com.mindbox.model.calendar_onboarding.CalendarMonthDataProviderInterface
import br.com.mindbox.model.calendar_onboarding.CalendarMonthItem
import java.text.SimpleDateFormat
import java.util.Locale

class CalendarMonthDataProvider : CalendarMonthDataProviderInterface {

    override fun getItems(): List<CalendarMonthItem> {
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)

        val months = mutableListOf<CalendarMonthItem>()

        // Adicionar o mês anterior
        val previousMonthIndex = if (currentMonth - 1 < 0) 11 else currentMonth - 1
        val previousYear = if (currentMonth - 1 < 0) currentYear - 1 else currentYear
        months.add(createCalendarMonthItem(previousMonthIndex, previousYear))

        // Adicionar o mês atual
        months.add(createCalendarMonthItem(currentMonth, currentYear))

        // Adicionar o próximo mês
        val nextMonthIndex = if (currentMonth + 1 > 11) 0 else currentMonth + 1
        val nextYear = if (currentMonth + 1 > 11) currentYear + 1 else currentYear
        months.add(createCalendarMonthItem(nextMonthIndex, nextYear))

        return months
    }

    private fun createCalendarMonthItem(monthIndex: Int, year: Int): CalendarMonthItem {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.MONTH, monthIndex)
            set(Calendar.YEAR, year)
        }
        val monthName = formatMonthName(calendar.get(Calendar.MONTH))
        val lastMonthName = formatMonthName(getPreviousMonth(calendar).get(Calendar.MONTH))
        val nextMonthName = formatMonthName(getNextMonth(calendar).get(Calendar.MONTH))
        return CalendarMonthItem(monthName, lastMonthName, nextMonthName)
    }

    private fun formatMonthName(month: Int): String {
        return SimpleDateFormat("MMMM", Locale("pt", "BR")).format(
            Calendar.getInstance().apply {
                set(Calendar.MONTH, month)
            }.time
        ).capitalize()
    }

    private fun getPreviousMonth(calendar: Calendar): Calendar {
        val previousCalendar = calendar.clone() as Calendar
        previousCalendar.add(Calendar.MONTH, -1)
        return previousCalendar
    }

    private fun getNextMonth(calendar: Calendar): Calendar {
        val nextCalendar = calendar.clone() as Calendar
        nextCalendar.add(Calendar.MONTH, 1)
        return nextCalendar
    }
}