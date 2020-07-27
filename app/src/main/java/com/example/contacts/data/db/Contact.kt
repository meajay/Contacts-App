package com.example.contacts.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


//API Response
//{"firstname":"john","lastname":"wick","email":"johnwick@lol.com","phone":"1111111","favorite":false}
//


@Entity(tableName = "contact_table",indices = [Index(value = ["email"],unique = true)])
class Contact(val firstname: String,
              val lastname:String,
              @PrimaryKey  var phone:String,
              val email :String,
              val favorite:Boolean): Serializable

