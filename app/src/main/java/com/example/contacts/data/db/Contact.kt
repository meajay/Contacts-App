package com.example.contacts.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//API Response
//{"firstname":"john","lastname":"wick","email":"johnwick@lol.com","phone":"1111111","favorite":false}


@Entity(tableName = "contact_table")
class Contact(val firstName: String,
              val lastName:String,
              val email :String,
              val phone:String,
              val favourite:Boolean){
    @PrimaryKey(autoGenerate = true) var id :Long? = null
}
