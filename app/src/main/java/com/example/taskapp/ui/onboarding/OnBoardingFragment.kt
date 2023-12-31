package com.example.taskapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp.data.local.Pref
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : Fragment() {

    private val adapter = OnBoardingAdapter(this::onClick)

    private lateinit var binding: FragmentOnBoardingBinding

    private val pref : Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.vp2Onboarding.adapter = adapter
        val viewPager2 = binding.vp2Onboarding
        viewPager2.adapter = adapter
        binding.wormDotsIndicator.attachTo(viewPager2)
    }

    private fun onClick() {
        pref.onBoardingShow()
        findNavController().navigate(R.id.navigation_home)
    }


            }

