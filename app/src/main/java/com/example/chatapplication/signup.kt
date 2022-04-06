package com.example.chatapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {

    private var TAG= "signupActivity"

    private lateinit var editname: EditText
    private lateinit var editemail : EditText
    private lateinit var password: EditText
    private lateinit var signup: Button

    private lateinit var mAuth: FirebaseAuth

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

            signup(email,pass)
        }
    }

    private fun signup(email:String,password:String){
        //logic for signing in a user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some error occured ",Toast.LENGTH_SHORT).show()
                     Log.d(TAG,"excep"+task.exception)

                }
            }

    }
}