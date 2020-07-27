package com.example.contacts.data.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


//API Response
//{"firstname":"john","lastname":"wick","email":"johnwick@lol.com","phone":"1111111","favorite":false}
//


@Entity(tableName = "contact_table",indices = [Index(value = ["email"],unique = true)])
class Contact(): Serializable{
    @SerializedName("firstname")
    var firstname: String? = null
    @SerializedName("lastname")
    var lastname:String? = null
    @SerializedName("phone")
    @PrimaryKey
    @NonNull
    var phone:String? = null
    @SerializedName("email")
    var email :String? = null
    @SerializedName("favorite")
    var favorite:Boolean = false


}

