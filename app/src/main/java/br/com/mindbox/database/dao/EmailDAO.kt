package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import br.com.mindbox.model.email.Email
import br.com.mindbox.model.email.EmailWithTasks

@Dao
interface EmailDAO {
    @Insert
    fun save(email: Email): Long

    @Update
    fun update(email: Email): Int

    @Delete
    fun delete(email: Email): Int

    @Transaction
    @Query("""
        SELECT * FROM tbl_email e 
        INNER JOIN tbl_email_recipient r ON e.email_id = r.email_id
        WHERE r.recipient_id IN (:recipientIds)
        AND e.send_date < DATETIME('now')
        ORDER BY e.send_date DESC
    """)
    fun findEmailsByRecipientIds(recipientIds: List<Long>): List<EmailWithTasks>

    @Transaction
    @Query("""
        SELECT * FROM tbl_email e 
        WHERE e.sender_id IN (:senderIds)
        ORDER BY e.send_date DESC
    """)
    fun findEmailsBySenderIds(senderIds: List<Long>): List<EmailWithTasks>

    @Transaction
    @Query("""
        SELECT * FROM tbl_email e
        INNER JOIN tbl_email_recipient r ON e.email_id = r.email_id
        WHERE r.recipient_id IN (:recipientIds) 
        AND r.email_category_id = :categoryId
        AND e.send_date < DATETIME('now')
        ORDER BY e.send_date DESC
    """)
    fun findEmailsByCategoryAndRecipientIds(recipientIds: List<Long>, categoryId: Long): List<EmailWithTasks>

    @Transaction
    @Query("""
        SELECT * FROM tbl_email e
        INNER JOIN tbl_email_recipient r ON e.email_id = r.email_id
        WHERE r.recipient_id IN (:recipientIds) 
        AND r.email_category_id = :categoryId
        AND r.finished_at IS NOT NULL
        AND e.send_date < DATETIME('now')
        ORDER BY e.send_date DESC
    """)
    fun findDeletedEmailsByCategoryAndRecipientIds(recipientIds: List<Long>, categoryId: Long): List<EmailWithTasks>

    @Transaction
    @Query("""
        SELECT * FROM tbl_email e
        INNER JOIN tbl_email_recipient r ON e.email_id = r.email_id
        WHERE r.recipient_id IN (:recipientIds) 
        AND r.finished_at IS NOT NULL
        AND e.send_date < DATETIME('now')
        ORDER BY e.send_date DESC
    """)
    fun findDeletedEmailsByRecipientIds(recipientIds: List<Long>): List<EmailWithTasks>


    @Transaction
    @Query("""
        SELECT * FROM tbl_email e
        INNER JOIN tbl_email_recipient r ON e.email_id = r.email_id
        WHERE r.recipient_id IN (:recipientIds)
        AND r.marked_as_spam_at IS NOT NULL
        ORDER BY e.send_date DESC
    """)
    fun findSpamEmailsByRecipientIds(recipientIds: List<Long>): List<EmailWithTasks>

    @Transaction
    @Query("""
        SELECT * FROM tbl_email e
        INNER JOIN tbl_email_recipient r ON e.email_id = r.email_id
        WHERE r.recipient_id IN (:recipientIds)
        AND r.email_category_id = :categoryId
        AND r.marked_as_spam_at IS NOT NULL
        ORDER BY e.send_date DESC
    """)
    fun findSpamEmailsByCategoryAndRecipientIds(recipientIds: List<Long>, categoryId: Long): List<EmailWithTasks>

    @Transaction
    @Query("""
        SELECT * FROM tbl_email
    """)
    fun findAll(): List<EmailWithTasks>
}