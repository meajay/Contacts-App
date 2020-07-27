package com.example.contacts.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Query("SELECT * from contact_table ORDER BY firstname COLLATE NOCASE  ASC")
    fun getAlphabetizedContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insert(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContactList(contact: List<Contact>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
     suspend fun updateContact( contact: Contact)
}