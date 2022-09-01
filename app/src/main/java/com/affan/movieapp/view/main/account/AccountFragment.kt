package com.affan.movieapp.view.main.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.affan.movieapp.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.optMyFavorite.setOnClickListener {
            // TODO: intent  MyFavoriteActivity
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