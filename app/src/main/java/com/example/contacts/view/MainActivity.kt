package com.example.contacts.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.db.Contact
import com.example.contacts.util.Constants
import com.example.contacts.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    private val newContactActivityRequestCode = 1
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var adapter : ContactListAdapter

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
            // Update the cached copy of the contacts in the adapter.
            contacts?.let { adapter.setContacts(it) }
        })
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
         adapter = ContactListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpButton(){
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivityForResult(intent, newContactActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newContactActivityRequestCode && resultCode == Activity.RESULT_OK) {
                  data?.getStringExtra(Constants.FIRST_NAME)?.let {
                    contactViewModel.insert(Contact(
                        it,
                        data.getStringExtra(Constants.LAST_NAME),
                        data.getStringExtra(Constants.PHONE_NUMBER),
                        data.getStringExtra(Constants.E_MAIL),
                        false))
                }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
