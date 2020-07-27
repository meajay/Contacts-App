package com.example.contacts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ContactRepository
import com.example.contacts.data.db.Contact
import com.example.contacts.data.db.ContactRoomDatabase
import com.example.contacts.data.network.ContactAPI
import com.example.contacts.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>
    private val tag: String = ContactRepository::class.java.name

    init {
        val contactDao = ContactRoomDatabase.getDatabase(application, viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }

    fun getAllContacts() {
        RetrofitClient.getRetrofitClient().create(ContactAPI::class.java).getContactList().enqueue(
            object : Callback<List<Contact>> {
                override fun onResponse(
                    call: Call<List<Contact>>,
                    response: Response<List<Contact>>
                ) {
                    insertList(response.body())
                }

                override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                    Log.e(tag, t.message.toString())
                }
            })
    }

    fun insertList(contacts: List<Contact>?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertContactsFromAPI(contacts)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateContact(contact)
    }
}