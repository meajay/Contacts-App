package com.example.contacts.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.contacts.R
import com.example.contacts.data.db.Contact
import com.example.contacts.util.Constants
import com.example.contacts.util.Utilities
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetail : AppCompatActivity() {

    private lateinit var favContact:CheckBox
    private lateinit var email:EditText
    private lateinit var phone:EditText
    private lateinit var backButton :ImageView
    private lateinit var editButton:TextView
    private lateinit var name:TextView
    private lateinit var contact : Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        initWidgets()
        getIntentData()
        setData()
    }

    private fun setData() {
        name.setText((contact.firstname + " "+contact.lastname))
        email.setText(contact.email)
        phone.setText(contact.phone)
        favContact.isChecked = contact.favorite

        backButton.setOnClickListener{onBackPressed()
        }
        editButton.setOnClickListener{
            if(!Utilities.isPhoneValid(phone.text.toString())){
                return@setOnClickListener
            }

            if(!Utilities.isEmailValid(email.text.toString())){
                return@setOnClickListener
            }
            setAndSendContact()
        }
    }

    private fun setAndSendContact() {
        val intent  = Intent()
        var contact : Contact = setContactData()
        intent.putExtra(Constants.CONTACT,contact)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    private fun setContactData(): Contact {
        val localContact = Contact()
        localContact.firstname = contact.firstname
        localContact.phone = phone.text.toString()
        localContact.lastname = contact.lastname
        localContact.email = email.text.toString()
        localContact.favorite = favContact.isChecked

        return localContact
    }

    private fun getIntentData() {
      val intent: Intent = intent
        contact = intent.extras?.getSerializable(Constants.CONTACT) as Contact
    }

    private fun initWidgets(){
        favContact = findViewById(R.id.checkboxFav)
        email = findViewById(R.id.userMail)
        phone = findViewById(R.id.userPhone)
        backButton = findViewById(R.id.backButton)
        editButton = findViewById(R.id.editAction)
        name = findViewById(R.id.contactName)
    }
}
