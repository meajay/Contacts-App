package com.example.contacts.data

import androidx.lifecycle.LiveData
import com.example.contacts.data.db.Contact
import com.example.contacts.data.db.ContactDao

class ContactRepository(private val contactDao: ContactDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allContacts: LiveData<List<Contact>> = contactDao.getAlphabetizedContacts()

    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }
}