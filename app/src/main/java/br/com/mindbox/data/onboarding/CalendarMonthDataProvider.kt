package br.com.mindbox.data.onboarding

import br.com.mindbox.model.calendar_onboarding.CalendarMonthDataProviderInterface
import br.com.mindbox.model.calendar_onboarding.CalendarMonthItem

class CalendarMonthDataProvider : CalendarMonthDataProviderInterface {
    override fun getItems(): List<CalendarMonthItem> {
        return listOf(
            CalendarMonthItem(
                currentMonth = "Janeiro",
                lastMonth = "",
                nextMonth = "Fev"
            ),
            CalendarMonthItem(
                currentMonth = "Fevereiro",
                lastMonth = "Jan",
                nextMonth = "Mar"
            ),
            CalendarMonthItem(
                currentMonth = "Março",
                lastMonth = "Fev",
                nextMonth = "Abr"
            ),
        )
    }
}