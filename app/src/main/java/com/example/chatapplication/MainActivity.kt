package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


// we initialise all the things that we need to put the elements to the arraylist


private lateinit var mAuth:FirebaseAuth
private lateinit var recycerView: RecyclerView
private lateinit var userlist:ArrayList<user>
private lateinit var adapter: UserAdapter
private lateinit var mDBref:DatabaseReference


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //we need to initialize mAuth in this activity as well to be able to get the names on the screen
        mAuth= FirebaseAuth.getInstance()

        mDBref=FirebaseDatabase.getInstance().reference

        userlist= ArrayList()

        adapter=UserAdapter(this ,userlist)

        //initialising the layout manager
        recycerView=findViewById(R.id.user_recyclerView)
        recycerView.layoutManager= LinearLayoutManager(this)
        recycerView.adapter= adapter


        //getting the information form the database
        //we go inside the user branch  and we get all the users informaton
        mDBref.child("user").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //what is Datasnapshot? -> anytime we read or write data from the firebase database we receive the data in the form of datasnapshot

                //before we run the for loop we need to clear the previous list as if we run the change the dataset for multiple times then as the loop runs for every
                //data entry each time , so we would get repeating data
                userlist.clear()
                for(postSnapshot in snapshot.children){
                    val currentuser=postSnapshot.getValue(user::class.java)

                    //we do the logged in users name in the list of the chat
                    if(mAuth.currentUser?.uid != currentuser?.UID) {
                        userlist.add(currentuser!!)
                    }

                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                //we do not have to do anything in this
            }

        })


    }

    //whenever we make a menu we need to call this funtion to inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Logic for when the logout button is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.log_out){
            //logic for logging a user out
            mAuth.signOut()
            //after we signout from the application we need to go back to the login activity
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
            //after we come back to the login activity we have to make sure that if we push the back button the application
            //closes,,, so we use the finish(this command basically is used not allow user to go back to the prev activity).
            finish()
            return true

        }
        return true


    }
}