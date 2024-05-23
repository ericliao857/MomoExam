package com.example.momoexam.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momoexam.R
import com.example.momoexam.databinding.ItemAreaIntroductionBinding
import com.example.momoexam.vo.introduction.AreaIntroduction

class IntroductionAdapter(
    private val items: List<AreaIntroduction>,
    private val onItemClick: (AreaIntroduction) -> Unit
) : RecyclerView.Adapter<IntroductionAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ItemAreaIntroductionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: AreaIntroduction,
            onItemClick: (AreaIntroduction) -> Unit
        ) {
            Glide.with(itemView)
                .load(item.ePicUrl)
                .into(binding.ivPic)
            binding.tvAreaName.text = item.eName
            binding.tvInfo.text = item.eInfo
            binding.tvMemo.text = item.eMemo.takeUnless { it.isNullOrEmpty() }
                ?: itemView.context.getString(R.string.no_closing_information)

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAreaIntroductionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                 false
            )
        )
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }
}