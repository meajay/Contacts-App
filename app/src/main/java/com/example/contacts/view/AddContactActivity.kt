package com.example.contacts.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.contacts.R
import com.example.contacts.util.Constants
import com.example.contacts.util.Utilities

class AddContactActivity : AppCompatActivity() {

    private lateinit var doneButton : TextView
    private lateinit var cancalButton : TextView
    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    private lateinit var email :EditText
    private lateinit var phone : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        setupWidgets()
        setUpListeners()
    }

    private fun setupWidgets() {
        doneButton = findViewById(R.id.doneAction)
        cancalButton = findViewById(R.id.cancelAction)
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        phone = findViewById(R.id.mobile)
        email = findViewById(R.id.email)
    }

    private fun setUpListeners() {
        doneButton.setOnClickListener {
            val replyIntent = Intent()
            if (isContactDataValid()) {
                replyIntent.putExtra(Constants.FIRST_NAME, firstName.text.toString())
                replyIntent.putExtra(Constants.LAST_NAME, lastName.text.toString())
                replyIntent.putExtra(Constants.PHONE_NUMBER, phone.text.toString())
                replyIntent.putExtra(Constants.E_MAIL, email.text.toString())

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        cancalButton.setOnClickListener{
            onBackPressed()}
    }

    private fun isContactDataValid(): Boolean {
        if(!Utilities.isNameValid(firstName.text.toString())){
           Utilities.showToast(this,getString(R.string.first_name_error))
            return false
        }
        else if(!Utilities.isNameValid(lastName.text.toString())){
            Utilities.showToast(this,getString(R.string.last_name_error))
            return false
        }

        else  if(!Utilities.isPhoneValid(phone.text.toString())){
            Utilities.showToast(this,getString(R.string.phone_number_error))
            return false
        }

        else if(!Utilities.isEmailValid(email.text.toString())){
            Utilities.showToast(this,getString(R.string.email_error))
            return false
        }

        return true
    }
}
