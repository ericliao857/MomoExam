package com.example.momoexam.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momoexam.R
import com.example.momoexam.databinding.FragmentDetailBinding
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


const val ARG_AREA = "area" // 場館資料
/**
 * 館區詳細介紹
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    initView(it.area)
                    setAnimalItems(it.animals)
                }
            }
        }
    }

    private fun initView(area: AreaIntroduction?) {
        area?.let {
            Glide.with(this)
                .load(it.ePicUrl)
                .into(binding.ivAreaPic)
            binding.tvInfo.text = it.eInfo
            binding.tvMemo.text = it.eMemo.takeUnless { memo -> memo.isNullOrEmpty() }
                ?: getString(R.string.no_closing_information)
            binding.tvCategory.text = it.eCategory
            binding.btnOpenWeb.setOnClickListener { _ ->
                openWebUrl(it.eUrl)
            }
        }
    }

    private fun setAnimalItems(animals: List<AnimalInfo>) {
        binding.rvAreaIntroduction.addItemDecoration(
            DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        )
        binding.rvAreaIntroduction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAreaIntroduction.adapter = AnimalAdapter(items = animals) {
            goToAnimalDetail(it)
        }
    }

    /**
     * 開啟資料內的連結
     */
    private fun openWebUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun goToAnimalDetail(animalInfo: AnimalInfo) {
        val direction = DetailFragmentDirections.actionNavDetailToNavAnimalDetail(animal = animalInfo)
        findNavController().navigate(direction)
    }
}