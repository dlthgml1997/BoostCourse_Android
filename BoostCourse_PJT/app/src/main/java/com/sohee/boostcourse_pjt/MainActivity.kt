package com.sohee.boostcourse_pjt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_main_act_thumb_up.setOnClickListener {
            if (it.isSelected) {
            }
        }
    }
}
