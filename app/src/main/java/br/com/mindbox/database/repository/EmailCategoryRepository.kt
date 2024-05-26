package br.com.mindbox.database.repository

import android.content.Context
import br.com.mindbox.database.dao.EmailCategoryDAO
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailCategoryName

class EmailCategoryRepository(context: Context) {
    private val db: EmailCategoryDAO = ConfigDb.getDatabase(context).emailCategoryDAO()

    fun save(emailCategory: EmailCategory): Long {
        return db.save(emailCategory)
    }

    fun update(emailCategory: EmailCategory): Int {
        return db.update(emailCategory)
    }

    fun delete(emailCategory: EmailCategory): Int {
        return db.delete(emailCategory)
    }

    fun insertAll(emailCategories: List<EmailCategory>) {
        return db.insertAll(emailCategories)
    }

    fun findByName(name: EmailCategoryName): EmailCategory? {
        return db.findByName(name)
    }
    fun findAll(): List<EmailCategory> {
        return db.findAll()
    }
}