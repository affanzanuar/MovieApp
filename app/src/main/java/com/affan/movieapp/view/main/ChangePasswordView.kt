package com.affan.movieapp.view.main

interface ChangePasswordView {

    fun onFailUpdate(error:String)
    fun onSuccessUpdate(newPass:String)

}