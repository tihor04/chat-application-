package com.example.chatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class messageadapter(val context : Context, val messageList:ArrayList<Message>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val ITEM_RECIVED=1
    val ITEM_SENT=2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType==1){
            //inflate receive
            val view: View= LayoutInflater.from(context).inflate(R.layout.recivedmessage,parent,false)
            return recivedViewHolder(view)
        }
        else
        {
            //inflate sent
            val view: View= LayoutInflater.from(context).inflate(R.layout.sendlayout,parent,false)
            return sendViewHolder(view)
        }
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

    // we need to tell the onCreateViewHolder that which view we need to inflate, therefore,we need to override the getItemViewType function
    // in this function we compare the uid of the current user to the sender/ receiver id

    override fun getItemViewType(position: Int): Int {

        val currentuser= messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentuser.senderId)){
            return ITEM_SENT
        }
        else
            return ITEM_RECIVED
    }

    override fun getItemCount(): Int {
        return messageList.size
    }



    //here as we have two views to be managed by the adapter. so,we need to make two viewholders..and we don't pass either of them to the adapter class

    class sendViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
   val sentmessage =itemView.findViewById<TextView>(R.id.txt_sentmessage)
    }

    class recivedViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
     val recived=itemView.findViewById<TextView>(R.id.txt_recivedmessage)
    }
}

