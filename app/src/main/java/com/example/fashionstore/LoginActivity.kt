package com.example.fashionstore
import com.example.fashionstore.R;
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity() {
    private var emailInput: EditText? = null
    private var passwordInput: EditText? = null
    private var loginButton: Button? = null
    private lateinit var signupRedirect: TextView
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById<EditText>(R.id.email)
        passwordInput = findViewById<EditText>(R.id.password)
        loginButton = findViewById<Button>(R.id.loginButton)
        signupRedirect = findViewById<TextView>(R.id.signupRedirect)
        mAuth = FirebaseAuth.getInstance()
        loginButton?.let { button ->
            button.setOnClickListener {
                loginUser()
            }
        }
        signupRedirect = findViewById(R.id.signupRedirect)
        signupRedirect.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun loginUser() {
        val email = emailInput!!.text.toString().trim { it <= ' ' }
        val password = passwordInput!!.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Email and Password required", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Failed: " + task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}