package com.example.piscum.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.piscum.R
import com.example.piscum.adapter.ImageAdapter
import com.example.piscum.databinding.FragmentHomeFeedBinding
import com.example.piscum.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFeedFragment : Fragment(R.layout.fragment_home_feed) {
    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeFeedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        viewModel.responseImage.observe(requireActivity()) { listImages ->
            imageAdapter.images = listImages
        }
    }

    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter()

        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}