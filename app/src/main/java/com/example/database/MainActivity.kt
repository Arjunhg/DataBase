package com.example.database

//Writing Code that will store data in firebase when user gives input.
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference /* Lateinit is a keyword in kotlin user to make a variable which we wants to define later. Its outside of oncreate method.
                                                  While DatabaseReference is its type like int,String are types of number and words*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val signupButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userID = findViewById<TextInputEditText>(R.id.etUsername)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signupButton.setOnClickListener {
            //Storing data entered by by the user in firebase

            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val unique = userID.text.toString()
            val password = userPassword.text.toString()

            val user = User(name, mail, password, unique) //making object of User class

            database = FirebaseDatabase.getInstance().getReference("Users")

            //Entering data in the firebase by making child of database

            database.child(unique).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
                userID.text?.clear()
                userPassword.text?.clear()

                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}