package com.example.contacts.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.db.Contact
import com.example.contacts.util.Constants
import com.example.contacts.util.callback.OnAdapterItemClick
import com.example.contacts.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity(), OnAdapterItemClick {

    private val newContactActivityRequestCode = 1
    private val updateExistingContactRequestCode = 1;
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setUpRecyclerView()
        setUpButton()

    }

    private fun initViewModel() {
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.allContacts.observe(this, Observer { contacts ->
            contacts?.let { adapter.setContacts(it) }
            contactViewModel.getAllContacts()
        })
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = ContactListAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpButton() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivityForResult(intent, newContactActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == newContactActivityRequestCode) {
                val contact = Contact()
                contact.firstname = data?.getStringExtra(Constants.FIRST_NAME)
                contact.lastname = data?.getStringExtra(Constants.LAST_NAME)
                contact.phone = data?.getStringExtra(Constants.PHONE_NUMBER)
                contact.email = data?.getStringExtra(Constants.E_MAIL)
                contactViewModel.insert(contact)

            } else if(requestCode == Constants.EDIT_CONTACT_REQUEST_CODE) {
                val contact : Contact = data?.getSerializableExtra(Constants.CONTACT) as Contact
                resultCode == Activity.RESULT_OK
            }
        }
        else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onAdapterItemClick(contact: Contact) {
            startContactDetailActivity(contact)
    }

    private fun startContactDetailActivity( contact:Contact){
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(Constants.CONTACT,contact)
        startActivityForResult(intent,Constants.EDIT_CONTACT_REQUEST_CODE)
    }
}
