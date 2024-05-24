package com.example.momoexam.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momoexam.R
import com.example.momoexam.databinding.ItemAnimalBinding
import com.example.momoexam.utils.Utils
import com.example.momoexam.vo.animal.AnimalInfo


class AnimalAdapter(
    private val items: List<AnimalInfo>,
    private val onItemClick: (AnimalInfo) -> Unit
): RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animalInfo: AnimalInfo, onItemClick: (AnimalInfo) -> Unit) {
            Glide.with(itemView)
                .load(animalInfo.aPic01Url)
                .into(binding.ivPic)
            binding.tvName.text = animalInfo.aNameCh
            val separate = itemView.context.getString(R.string.separate)
            binding.tvInfo.text = Utils.mergeStringsWithSeparator(
                baseString = animalInfo.aAlsoknown,
                appendString = animalInfo.aKeywords,
                separator = separate
            )
            itemView.setOnClickListener { onItemClick(animalInfo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimalBinding.inflate(
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