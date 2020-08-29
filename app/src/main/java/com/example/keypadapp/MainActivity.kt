package com.example.keypadapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.keypadapp.keypadUtils.KeyboardHeightProvider
import com.example.keypadapp.utils.BaseKeyboardAnimator
import com.example.keypadapp.utils.SimpleBottomSheetDialog
import com.example.keypadapp.utils.SimpleKeyboardAnimator
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val animator: BaseKeyboardAnimator by lazy(LazyThreadSafetyMode.NONE) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            SimpleKeyboardAnimator(window)
        } else {
            TODO("VERSION.SDK_INT < KITKAT_WATCH")
        }
    }
    private var keyboardHeightProvider: KeyboardHeightProvider? = null
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.fab_id)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        SimpleBottomSheetDialog(this, true).show()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            animator.start()
        }
        keyboardHeightProvider = KeyboardHeightProvider(this)
        keyboardHeightProvider?.addKeyboardListener(getKeyboardListener())
    }

    override fun onResume() {
        super.onResume()
        keyboardHeightProvider?.onResume()
    }

    override fun onPause() {
        super.onPause()
        keyboardHeightProvider?.onPause()
    }


    private fun getKeyboardListener() = object : KeyboardHeightProvider.KeyboardListener {
        override fun onHeightChanged(height: Int) {
            if (height < 0) {
                changeFABPosition(0)
            } else {
                changeFABPosition(height)
            }
        }
    }

    private fun changeFABPosition(height: Int) {
//        val params = (fab?.layoutParams as? FrameLayout.LayoutParams?)
//        params?.bottomMargin = 16.dp + height
//        fab?.layoutParams = params

//        fab?.animate()?.translationY(-height.toFloat())?.duration = 400
    }

}