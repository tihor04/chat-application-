package com.example.chatapplication

import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.Context

class messageadapter( var context: Context,var messageList:ArrayList<Message>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentmessage=messageList[position]

        if(holder.javaClass==sendViewHolder::class.java){
            //logic if we have sent a message

            val viewholder= holder as sendViewHolder
            holder.sentmessage.text = currentmessage.message

        }
        else{
            //logic if we have received a message

            var viewholder=holder as recivedViewHolder
            holder.recived.text=currentmessage.message

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }



    //here as we have two views to be managed by the adapter. so,we need to make two viewholders..and we don't pass either of them to the adapter class

    class sendViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
   val sentmessage =itemView.findViewById<TextView>(R.id.txt_sentmessage)
    }

    class recivedViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
     val recived=itemView.findViewById<TextView>(R.id.txt_recivedmessage)
    }
}

