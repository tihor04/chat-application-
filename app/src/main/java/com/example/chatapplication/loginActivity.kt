package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {

    private lateinit var editemail :EditText
    private lateinit var password: EditText
    private lateinit var login:Button
    private lateinit var signup:Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       // the next line of code is used to initialise the firebase auth.
        mAuth= FirebaseAuth.getInstance()



        editemail=findViewById(R.id.email)
        password=findViewById(R.id.password)
        login=findViewById(R.id.button_login)
        signup=findViewById(R.id.signup)

        signup.setOnClickListener {
        val intent= Intent(this,com.example.chatapplication.signup::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
          val email= editemail.text.toString()
            val pass=password.text.toString()


            login(email,pass)
        }

    }

    private fun login(email:String,password:String){
   //logic for logging in  a user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)


                } else {
                    Toast.makeText(this,"User not found",Toast.LENGTH_SHORT).show();

                }
            }
    }
}