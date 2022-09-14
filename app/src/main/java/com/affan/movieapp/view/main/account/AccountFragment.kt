package com.affan.movieapp.view.main.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.affan.movieapp.databinding.FragmentAccountBinding
import com.affan.movieapp.view.main.account.myfavorite.FavoriteActivity

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
//      (activity as AppCompatActivity).supportActionBar?.hide()

        binding.optMyFavorite.setOnClickListener {
            Intent(context,FavoriteActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.optEditProfile.setOnClickListener {
            // TODO: intent  EditProfileActivity
        }
        binding.optAbout.setOnClickListener {
            // TODO: intent  AboutActivity
        }
        binding.optLogout.setOnClickListener {
            // TODO: intent  Logout
        }

    }

}