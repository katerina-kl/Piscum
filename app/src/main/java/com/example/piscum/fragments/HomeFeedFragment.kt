package com.example.piscum.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.piscum.R
import com.example.piscum.adapter.ImageAdapter
import com.example.piscum.databinding.FragmentHomeFeedBinding
import com.example.piscum.models.Image
import com.example.piscum.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFeedFragment : Fragment(R.layout.fragment_home_feed) {

    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageViewModel by viewModels()

    private lateinit var imageAdapter: ImageAdapter
    private var imagesList = arrayListOf<Image>()

    var page = 1
    var isLoading = false
    val limit = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        viewModel.responseImage.observe(viewLifecycleOwner) { listImages ->
            imagesList.addAll(listImages)
            imageAdapter.notifyDataSetChanged()
            isLoading = false
            binding.progressBar.visibility = View.GONE
        }

        getPage()
    }


    private fun setUpRecyclerView() {
        imageAdapter = ImageAdapter(requireContext(),imagesList)

        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = imageAdapter.itemCount

                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        page++
                        getPage()
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun getPage() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getAllImages(page, limit)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}