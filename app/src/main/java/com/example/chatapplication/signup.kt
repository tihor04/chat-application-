package com.example.chatapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup : AppCompatActivity() {

    private var TAG= "signupActivity"

    private lateinit var editname: EditText
    private lateinit var editemail : EditText
    private lateinit var password: EditText
    private lateinit var signup: Button

    private lateinit var mAuth: FirebaseAuth

    private lateinit var mDBRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth=FirebaseAuth.getInstance()



        editname = findViewById(R.id.username)
        editemail=findViewById(R.id.email)
        password=findViewById(R.id.password)
        signup=findViewById(R.id.signup)


        signup.setOnClickListener {
            val email=editemail.text.toString().trim()
            val pass=password.text.toString().trim()
            val name=editname.text.toString().trim()

            signup(name,email,pass)
        }
    }

    private fun signup(name:String,email:String,password:String){
        //logic for signing in a user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,
                        //we first need to add the new user to our database
                            addusertodatabase(name,email,mAuth.currentUser?.uid!! )
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some error occured ",Toast.LENGTH_SHORT).show()
                     Log.d(TAG,"excep"+task.exception)

                }
            }

    }

    private fun addusertodatabase(name:String,email:String,uid:String){

        //function to add a new user to the database so that we are able to fetch the information of each users in our app

         mDBRef=FirebaseDatabase.getInstance().reference

        mDBRef.child("user").child(uid).setValue(user(name,email,uid))

    }
}