package com.example.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

// we initialise all the things that we need to put the elements to the arraylist



private lateinit var recycerView: RecyclerView
private lateinit var userlist:ArrayList<user>
private lateinit var adapter: UserAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userlist= ArrayList()

        
        adapter=UserAdapter(this ,userlist)

    }
}