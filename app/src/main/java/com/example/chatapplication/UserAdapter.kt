package com.example.chatapplication

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter( val context: Context,val userList:ArrayList<user>,) : RecyclerView.Adapter<userViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {

        //in this we will inflate a view

        val view: View= LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        //we return the userviewholder and pass in the view that we just have created

        return userViewHolder(view)

    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {

        //we need to attatch the things that we get back from the request to our view

        val currentuser = userList[position]

        holder.textname.text=currentuser.name



    }

    override fun getItemCount(): Int {

      // here we just have to return the size of our, arraylist that we have to display

        return userList.size
    }
}

class userViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    // the main function of this class is to just initialise all the views that are present in each of the entities of our recycler View.

  val textname:TextView=itemView.findViewById(R.id.txt_name)
}