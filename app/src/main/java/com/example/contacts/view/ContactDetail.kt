package com.example.contacts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.contacts.R

class ContactDetail : AppCompatActivity() {

    private lateinit var favContact:CheckBox
    private lateinit var email:EditText
    private lateinit var phone:EditText
    private lateinit var backButton :ImageView
    private lateinit var editButton:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        initWidgets()
        getIntentData()
    }

    private fun getIntentData() {
        var intentData : Bundle = getIntent().();
    }

    private fun initWidgets(){
        favContact = findViewById(R.id.checkboxFav)
        email = findViewById(R.id.userMail)
        phone = findViewById(R.id.userPhone)
        backButton = findViewById(R.id.backButton)
        editButton = findViewById(R.id.editAction)
    }
}
