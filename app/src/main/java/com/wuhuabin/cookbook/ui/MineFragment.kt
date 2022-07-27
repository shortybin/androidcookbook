package com.wuhuabin.cookbook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dylanc.viewbinding.binding
import com.wuhuabin.cookbook.R
import com.wuhuabin.cookbook.databinding.CategoryFragmentBinding
import com.wuhuabin.cookbook.databinding.MineFragmentBinding

class MineFragment : Fragment(R.layout.mine_fragment) {

    private val binding: MineFragmentBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}