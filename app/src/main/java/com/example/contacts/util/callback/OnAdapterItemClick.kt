package com.example.contacts.util.callback

import com.example.contacts.data.db.Contact

interface OnAdapterItemClick {
    fun onAdapterItemClick( contact: Contact)
}