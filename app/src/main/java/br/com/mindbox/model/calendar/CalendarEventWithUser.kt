package br.com.mindbox.model.calendar

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import br.com.mindbox.model.user.User

data class CalendarEventWithUser(
    @Embedded var calendarEvent: CalendarEvent,
    @Relation(
        parentColumn = "calendar_event_id", // Coluna na tabela CalendarEvent
        entityColumn = "user_id", // Coluna na tabela User
        associateBy = Junction(CalendarEventParticipant::class)
    )
    var participants: List<User>
)
