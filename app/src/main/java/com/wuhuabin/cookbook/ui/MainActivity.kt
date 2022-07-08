package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.viewmodel.UserViewModel

class MainActivity : BaseActivity() {
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.text_view).setOnClickListener {
            userViewModel.login()
        }
    }
}