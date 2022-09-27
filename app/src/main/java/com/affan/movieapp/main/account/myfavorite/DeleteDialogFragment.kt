package com.affan.movieapp.main.account.myfavorite

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.affan.movieapp.R
import com.affan.movieapp.databinding.FragmentDeleteDialogBinding


class DeleteDialogFragment (
    private val onDeleteFavorite : ()-> Unit,
        ): DialogFragment() {


    private lateinit var binding : FragmentDeleteDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteDialogBinding.inflate(layoutInflater,container,false)
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.btnOk.setOnClickListener {
            dismiss()
            onDeleteFavorite()
        }
    }
}