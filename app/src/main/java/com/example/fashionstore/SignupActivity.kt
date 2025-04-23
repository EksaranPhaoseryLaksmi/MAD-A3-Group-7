package com.example.fashionstore
import com.example.fashionstore.R;
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {
    private var emailInput: EditText? = null
    private var passwordInput: EditText? = null
    private lateinit var signupButton: Button
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        emailInput = findViewById<EditText>(R.id.email)
        passwordInput = findViewById<EditText>(R.id.password)
        signupButton = findViewById<Button>(R.id.signupButton)
        mAuth = FirebaseAuth.getInstance()
        signupButton = findViewById(R.id.signupButton)
        signupButton.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser() {
        val email = emailInput!!.text.toString().trim { it <= ' ' }
        val password = passwordInput!!.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Email and Password required", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Signup Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(
                            this@SignupActivity,
                            LoginActivity::class.java
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this@SignupActivity,
                        "Signup Failed: " + task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}