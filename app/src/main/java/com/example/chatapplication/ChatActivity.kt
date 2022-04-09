package com.example.chatapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView:RecyclerView
    private lateinit var messageBox:EditText
    private lateinit var sendBtn:ImageView
    private lateinit var messagelist:ArrayList<Message>
    private lateinit var mDBref:DatabaseReference
    private lateinit var messageadapter:messageadapter


    //using a sender and a  receiver room we are able to create a separate room for sender and receiver so that the message doesn't show in everyone's chat
    var ReciverRoom:String?=null
    var senderRoom:String?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //recive the information from the intent


        val name =intent.getStringExtra("name")
        val reciveruid =intent.getStringExtra("uid")

        supportActionBar?.title=name

        val senderuid=FirebaseAuth.getInstance().currentUser?.uid

        senderRoom=reciveruid+senderuid

        ReciverRoom=senderuid+reciveruid



        mDBref=FirebaseDatabase.getInstance().reference

        chatRecyclerView=findViewById(R.id.chat_recyclerView)
        messageBox=findViewById(R.id.messageBox)
        sendBtn=findViewById(R.id.sendimage)

        messagelist= ArrayList()

        messageadapter= messageadapter(this,messagelist)

        chatRecyclerView.layoutManager=LinearLayoutManager(this)
        chatRecyclerView.adapter=messageadapter

        //logic for adding data in recycler view from the database

        mDBref.child("chats").child(senderRoom!!).child("message").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                messagelist.clear()
                for(postsnapshot in snapshot.children){
                    val message= postsnapshot.getValue(Message::class.java)
                    messagelist.add(message!!)
                }
                messageadapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        //add message to database
        sendBtn.setOnClickListener{
          val message= messageBox.text.toString()
            val messageobj=Message(message,senderuid)

            mDBref.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageobj).addOnSuccessListener {
                    mDBref.child("chats").child(ReciverRoom!!).child("messages").push()
                        .setValue(messageobj)
                }
            messageBox.setText("")


        }








    }
}