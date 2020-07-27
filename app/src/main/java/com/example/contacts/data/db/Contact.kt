package com.example.contacts.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


//API Response
//{"firstname":"john","lastname":"wick","email":"johnwick@lol.com","phone":"1111111","favorite":false}
//


@Entity(tableName = "contact_table",indices = [Index(value = ["email"],unique = true)])
class Contact(val firstName: String,
              val lastName:String,
              @PrimaryKey  var phone:String,
              val email :String,
              val favourite:Boolean)

