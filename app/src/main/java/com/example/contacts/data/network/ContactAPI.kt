package com.example.contacts.data.network

import com.example.contacts.data.db.Contact
import com.example.contacts.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ContactAPI {
    @GET(Constants.CONTACT_END_POINT)
    fun getContactList(): Call<List<Contact>>

}