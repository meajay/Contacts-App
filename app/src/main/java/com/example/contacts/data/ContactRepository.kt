package com.example.contacts.data

import androidx.lifecycle.LiveData
import com.example.contacts.data.db.Contact
import com.example.contacts.data.db.ContactDao


class ContactRepository(private val contactDao: ContactDao) {


    val allContacts: LiveData<List<Contact>> = contactDao.getAlphabetizedContacts()

     suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun insertContactsFromAPI(contacts:List<Contact>?){
        contactDao.insertContactList(contacts)
    }
}