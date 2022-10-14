package com.affan.movieapp.presentation.main.account

interface ChangePasswordView {

    fun onFailUpdate(error:String)
    fun onSuccessUpdate(newPass:String)

}