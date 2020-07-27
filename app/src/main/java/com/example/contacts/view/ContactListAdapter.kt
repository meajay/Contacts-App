package com.example.contacts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.db.Contact
import com.example.contacts.util.callback.OnAdapterItemClick

class ContactListAdapter internal constructor(
        context: Context , val listener : OnAdapterItemClick
) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contactList = emptyList<Contact>()

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.fullName)
        val favImage:ImageView = itemView.findViewById(R.id.userFav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = contactList[position]
        holder.fullName.text = (current.firstname+ " "+ current.lastname)
        if(current.favorite){
            holder.favImage.visibility = View.VISIBLE
        }
        else{
            holder.favImage.visibility = View.GONE
        }

        holder.itemView.setOnClickListener{
            listener.onAdapterItemClick(current)
        }
    }

    internal fun setContacts(contacts: List<Contact>) {
        this.contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount() = contactList.size
}