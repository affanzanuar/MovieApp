package com.affan.movieapp.view.main.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityChangePasswordBinding
import com.affan.movieapp.view.main.ChangePasswordView

class ChangePasswordActivity : AppCompatActivity(),ChangePasswordView {

    private lateinit var binding: ActivityChangePasswordBinding

    private lateinit var changePasswordPresenter: ChangePasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        changePasswordPresenter = ChangePasswordPresenter(
            changePasswordView = this,
            pass = "123"
        )

        binding.btnUpdatePass.setOnClickListener {
            changePasswordPresenter.matchNewPass(
                chpass1 = binding.newPass.text.toString(),
                chpass2 = binding.confirmNewPass.text.toString()
            )
        }


    }


    override fun onFailUpdate(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessUpdate(newPass: String) {
        Toast.makeText(this, "Password Updated!", Toast.LENGTH_SHORT).show()
    }


}