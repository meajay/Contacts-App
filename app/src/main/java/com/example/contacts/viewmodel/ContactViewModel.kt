package com.example.contacts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ContactRepository
import com.example.contacts.data.db.Contact
import com.example.contacts.data.db.ContactRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository
    // Using LiveData and caching what getAlphabetizedContacts returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allContacts: LiveData<List<Contact>>

    init {
        val sDao = ContactRoomDatabase.getDatabase(application,viewModelScope).contactDao()
        repository = ContactRepository(sDao)
        allContacts = repository.allContacts
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }
}