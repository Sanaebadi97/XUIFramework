package com.tpa.xuiframework.form.validator

import android.widget.EditText
import com.hvd.xcore.XUtil
import com.tpa.xuiframework.form.Validator

class EmailValidator(private val mandatory: Boolean = false) : Validator {
    override fun isValid(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty() && !mandatory){
            return true
        }
        return XUtil.isEmailValid(editText.text.toString())
    }
}