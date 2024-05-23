package com.example.momoexam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.momoexam.databinding.FragmentHomeBinding
import com.example.momoexam.vo.introduction.AreaIntroduction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    setAreaIntroductionItem(it.items)
                }
        }
    }

    /**
     * 設定館區簡介列表
     */
    private fun setAreaIntroductionItem(items: List<AreaIntroduction>) {
        binding.rvAreaIntroduction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAreaIntroduction.addItemDecoration(
            DividerItemDecoration(requireContext(), VERTICAL)
        )
        binding.rvAreaIntroduction.adapter = IntroductionAdapter(items = items) {
            goToDetail(areaIntroduction = it)
        }
    }

    /**
     * 前往館區介紹頁面
     */
    private fun goToDetail(areaIntroduction: AreaIntroduction) {
        val directions = HomeFragmentDirections.actionNavHomeToDetail(areaIntroduction)
        findNavController().navigate(directions)
    }
}