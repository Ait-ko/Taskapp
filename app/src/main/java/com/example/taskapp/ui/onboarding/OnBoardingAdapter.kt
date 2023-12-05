package com.example.taskapp.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskapp.R
import com.example.taskapp.databinding.ItemOnboardingBinding
import com.example.taskapp.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf<OnBoarding>(
        OnBoarding(
            R.raw.animmation2,
            "TITLE",
            "deck1"
        ),
        OnBoarding(R.raw.animmation2,
            "TITLE2",
            "desc2"
        ),
        OnBoarding(R.raw.animation3
            ,"TITLE3",
            "desc3"
        ),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(boarding: OnBoarding) = with(binding) {
            with(boarding) {
                tvTitle.text = boarding.title
                tvDescription.text = boarding.description
                boarding.image?.let { imgV.setAnimation(it) }
                btnStart.isVisible = adapterPosition == list.lastIndex
                tvSkip.isVisible = adapterPosition != list.lastIndex
            }
            tvSkip.setOnClickListener {
                onClick()
            }
            btnStart.setOnClickListener {
                onClick()
            }
        }
    }
}