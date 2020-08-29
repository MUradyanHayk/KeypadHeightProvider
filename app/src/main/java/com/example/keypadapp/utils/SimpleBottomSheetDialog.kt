package com.example.keypadapp.utils

import android.content.Context
import android.view.WindowManager
import com.example.keypadapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class SimpleBottomSheetDialog(context: Context, private val enableAnimation: Boolean) : BottomSheetDialog(context) {

    init {
        setContentView(R.layout.dialog_simple_bottom_sheet)
    }

    override fun onStart() {
        super.onStart()
        window?.let { window ->
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            if (enableAnimation) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
                    SimpleKeyboardAnimator(window).start()
                }
            }
        }
    }
}