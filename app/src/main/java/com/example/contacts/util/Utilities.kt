package com.example.contacts.util

import android.content.Context
import android.widget.Toast

class Utilities {
    companion object{
        fun isNameValid(name:String):Boolean{
            if(name.length>1){
                return true
            }
            return false
        }

        fun isPhoneValid(number:String):Boolean{
            if(number.length in 8..15){
                return true
            }
            return false
        }

        fun isEmailValid(email:String):Boolean{
            if(email.length>7 &&email.contains(".com") && email.contains("@")){
                return true
            }
            return false
        }

        fun showToast(addContactActivity: Context, message: String) {
            Toast.makeText(addContactActivity,message,Toast.LENGTH_SHORT).show()
        }
    }

}