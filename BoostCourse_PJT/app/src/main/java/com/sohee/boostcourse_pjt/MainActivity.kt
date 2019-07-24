package com.sohee.boostcourse_pjt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import android.view.GestureDetector.OnGestureListener as OnGestureListener1

class MainActivity : AppCompatActivity() {

    var count_up: Int = 0
    var count_down: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_main_act_thumb_up.setOnClickListener {
            when {
                it.isSelected -> {
                    it.isSelected = false
                    count_up--
                    txt_main_act_thumb_up.text = count_up.toString()
                }
                btn_main_act_thumb_down.isSelected -> {
                    btn_main_act_thumb_down.isSelected = false
                    count_down--
                    txt_main_act_thumb_down.text = count_down.toString()
                }
                else -> {
                    it.isSelected = true
                    count_up++
                    txt_main_act_thumb_up.text = count_up.toString()

                }
            }
        }
        btn_main_act_thumb_down.setOnClickListener {
            when {
                it.isSelected -> {
                    it.isSelected = false
                    count_down--
                    txt_main_act_thumb_down.text = count_down.toString()
                }
                btn_main_act_thumb_up.isSelected -> {
                    btn_main_act_thumb_up.isSelected = false
                    count_up--
                    txt_main_act_thumb_up.text = count_up.toString()
                }
                else -> {
                    it.isSelected = true
                    count_down++
                    txt_main_act_thumb_down.text = count_down.toString()
                }
            }
        }
        btn_main_act_reserve.setOnClickListener {
            toast("눌렸습니다")
        }
        btn_main_act_detail.setOnClickListener {
            toast("눌렸습니다")
        }
    }
}