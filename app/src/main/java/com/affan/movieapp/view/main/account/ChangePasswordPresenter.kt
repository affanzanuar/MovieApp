package com.affan.movieapp.view.main.account

import com.affan.movieapp.view.main.ChangePasswordView

class ChangePasswordPresenter(
    private val changePasswordView: ChangePasswordView,
    private val pass: String
) {

    fun matchNewPass(chpass1: String, chpass2: String) {
        if (chpass1 == chpass2 && chpass1.isNotEmpty() && chpass2.isNotEmpty()) {
            checkPrevPass(chpass1, chpass2)
        }
        if (chpass1 != chpass2 && chpass1.isNotEmpty() && chpass2.isNotEmpty()) {
            changePasswordView.onFailUpdate(
                error = "Confirm password does not match with the new password"
            )
        }
        if (chpass1.isEmpty() || chpass2.isEmpty()) {
            changePasswordView.onFailUpdate(
                error = "Your new password cannot be empty"
            )
        } else {
            return
        }
    }

    private fun checkPrevPass(chpass1: String, chpass2: String) {
        if (pass == chpass1 || pass == chpass2) {
            changePasswordView.onFailUpdate(
                error = "Your new password cannot be the same as your current password"
            )
        } else {
            updatePass(newPass = chpass1)
        }
        return
    }

    private fun updatePass(newPass: String) {
        changePasswordView.onSuccessUpdate(newPass)
    }

}