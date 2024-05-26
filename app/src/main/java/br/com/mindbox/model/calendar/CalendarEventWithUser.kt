package br.com.mindbox.model.calendar

import androidx.room.Embedded
import androidx.room.Relation
import br.com.mindbox.model.user.User

data class CalendarEventWithUser(
    @Embedded var calendarEvent: CalendarEvent,
    @Relation(
        parentColumn = "participants_id",
        entityColumn = "user_id"
    )
    var participants: List<User>
)
