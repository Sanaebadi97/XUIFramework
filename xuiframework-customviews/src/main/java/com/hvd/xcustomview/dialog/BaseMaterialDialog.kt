package com.hvd.xcustomview.dialog

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import com.hvd.xcore.XUtil
import com.hvd.xcore.extention.addView
import com.hvd.xcustomview.R
import kotlinx.android.synthetic.main.material_dialog.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.textColor

abstract class BaseMaterialDialog(context: Context, okListener: ((Dialog) -> Unit)): Dialog(context,
    R.style.DialogTheme
) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.material_dialog)

        btnAccept.textColor = XUtil.getAccentColor(context)
        btnCancel.textColor = XUtil.getAccentColor(context)

        btnAccept.setOnClickListener {
            okListener(this)
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        if (getContent() != null){
            frameContent.addView(
                layoutInflater.inflate(this.getContent()!!, frameContent, false)
            )
        } else if (getAnkoContent() != null){
            frameContent.addView(context, this.getAnkoContent()!!)
        }

    }

    protected open fun getContent(): Int? {
        return null
    }

    protected open fun getAnkoContent(): AnkoComponent<ViewGroup>? {
        return null
    }

}