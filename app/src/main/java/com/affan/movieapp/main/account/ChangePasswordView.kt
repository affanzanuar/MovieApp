package com.affan.movieapp.main.account

interface ChangePasswordView {

    fun onFailUpdate(error:String)
    fun onSuccessUpdate(newPass:String)

}