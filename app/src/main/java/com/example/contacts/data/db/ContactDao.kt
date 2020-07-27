package com.example.contacts.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Query("SELECT * from contact_table ORDER BY firstName ASC")
    fun getAlphabetizedContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateContact( contact: Contact)
}