package com.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.expensetracker.LoginSignup.LoginSignup.LoginActivity
import com.expensetracker.databinding.ActivitySignupBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Data classes for API request and response
data class SignupRequest(
    val name: String,
    val email: String,
    val password: String
)

data class SignupResponse(
    val status: String,
    val message: String
)

// Retrofit API interface
interface ApiService {
    @POST("api/register") // Replace with your friend's actual endpoint
    suspend fun registerUser(@Body request: SignupRequest): SignupResponse
}

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your-friends-api.com/") // Replace with your friend's API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        // Handle Sign Up button click
        binding.signupBtn.setOnClickListener {
            val name = binding.nameTxt.text.toString().trim()
            val email = binding.emailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString().trim()
            val confirmPassword = binding.confirmPasswordTxt.text.toString().trim()

            if (validateInput(name, email, password, confirmPassword)) {
                registerUser(name, email, password)
            }
        }

        // Handle Login text click
        binding.loginTxt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Handle Google Sign-In (placeholder)
        binding.googleBtn.setOnClickListener {
            Toast.makeText(this, "Google Sign-In not implemented", Toast.LENGTH_SHORT).show()
            // Implement Google Sign-In using GoogleSignInClient
        }

        // Handle Facebook Sign-In (placeholder)
        binding.facebookBtn.setOnClickListener {
            Toast.makeText(this, "Facebook Sign-In not implemented", Toast.LENGTH_SHORT).show()
            // Implement Facebook Login using Facebook SDK
        }
    }

    private fun validateInput(name: String, email: String, password: String, confirmPassword: String): Boolean {
        if (name.isEmpty()) {
            binding.nameTxt.error = "Name is required"
            return false
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailTxt.error = "Valid email is required"
            return false
        }
        if (password.isEmpty() || password.length < 6) {
            binding.passwordTxt.error = "Password must be at least 6 characters"
            return false
        }
        if (password != confirmPassword) {
            binding.confirmPasswordTxt.error = "Passwords do not match"
            return false
        }
        return true
    }

    private fun registerUser(name: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = SignupRequest(name, email, password)
                val response = apiService.registerUser(request)
                withContext(Dispatchers.Main) {
                    if (response.status == "success") {
                        Toast.makeText(this@SignupActivity, response.message, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@SignupActivity, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignupActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}