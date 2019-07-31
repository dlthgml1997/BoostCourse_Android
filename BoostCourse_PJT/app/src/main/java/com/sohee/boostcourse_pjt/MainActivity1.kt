package com.sohee.boostcourse_pjt

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_review.*
import kotlinx.android.synthetic.main.list_item_review.view.*
import org.jetbrains.anko.toast

class MainActivity1 : AppCompatActivity() {

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

        val adapter : ReviewAdapter = ReviewAdapter()
        adapter.addItem(ReviewItem("shfk***","10분전","4","재밌어요!"))
        adapter.addItem(ReviewItem("dlth***","15분전","3","그저 그랬어요"))
        lv_main_act_review.adapter = adapter
    }

    inner class ReviewAdapter : BaseAdapter() {

        var items: ArrayList<ReviewItem> = ArrayList()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = LayoutInflater.from(this@MainActivity1).inflate(R.layout.list_item_review, parent, false)

            var item : ReviewItem = items.get(position)
            view.txt_review_item_id.text = item.getId()
            view.txt_review_item_time!!.text = item.getTime()
            view.rb_review_item_rating!!.rating = item.getRating().toFloat()
            view.txt_review_item_review!!.text = item.getReview()

            return view
        }

        fun addItem(item : ReviewItem) {
            items.add(item)
        }

        override fun getItem(p0: Int): Any {
            return items.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int = items.size
    }
}