package com.affan.movieapp.view.main.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}