package com.wuhuabin.cookbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wuhuabin.common.base.BaseActivity
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.ActivityMainBinding
import com.wuhuabin.cookbook.viewmodel.UserViewModel

class MainActivity : BaseActivity() {
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(layoutInflater)
        setContentView(inflate.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment

        val navController = navHostFragment.navController
        inflate.bottomNavigationView.setupWithNavController(navController)
    }
}