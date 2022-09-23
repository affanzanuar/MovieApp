package com.affan.movieapp.main.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.affan.movieapp.databinding.FragmentAccountBinding
import com.affan.movieapp.main.account.myfavorite.FavoriteActivity
import com.affan.movieapp.main.account.about.AboutActivity
import com.affan.movieapp.main.account.editprofile.EditProfileActivity

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
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }
        binding.optAbout.setOnClickListener {
            startActivity(Intent(activity, AboutActivity::class.java))
        }
        binding.optLogout.setOnClickListener {
            // TODO: intent  Logout
        }

    }

}