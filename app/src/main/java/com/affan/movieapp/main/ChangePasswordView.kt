package com.affan.movieapp.main

interface ChangePasswordView {

    fun onFailUpdate(error:String)
    fun onSuccessUpdate(newPass:String)

}