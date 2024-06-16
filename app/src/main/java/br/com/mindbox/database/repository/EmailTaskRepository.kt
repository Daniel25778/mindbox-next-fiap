package br.com.mindbox.database.repository

import android.content.Context
import br.com.mindbox.database.dao.EmailTaskDAO
import br.com.mindbox.database.settings.ConfigDb

class EmailTaskRepository(context: Context) {
    private val db: EmailTaskDAO = ConfigDb.getDatabase(context).emailTaskDAO()

    fun changeCompletedStatus(emailTaskId: Long, isCompleted: Boolean) {
        return db.changeCompletedStatus(emailTaskId, isCompleted)
    }
}