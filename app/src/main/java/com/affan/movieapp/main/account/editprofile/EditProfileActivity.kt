package com.affan.movieapp.main.account.editprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityEditProfileBinding
import com.affan.movieapp.main.account.ChangePasswordActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        
        binding.changePicture.setOnClickListener {
            // TODO: Change Profile Picture feature 
        }
        
        binding.changePass.setOnClickListener {
            startActivity(Intent(this,ChangePasswordActivity::class.java))
        }


    }
}