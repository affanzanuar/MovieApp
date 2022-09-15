package com.affan.movieapp.view.main.account.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        binding = ActivityAboutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}