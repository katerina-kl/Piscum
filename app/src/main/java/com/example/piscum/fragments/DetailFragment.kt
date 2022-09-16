package com.example.piscum.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.piscum.R
import com.example.piscum.databinding.FragmentDetailBinding
import com.example.piscum.models.Image

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var image: Image

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = args.image

        populateUi()
    }

    private fun populateUi() {

        binding.apply {
            imageView.load(image.download_url)
            author.text = image.author
            width.text = image.width.toString()
            height.text = image.height.toString()
        }
        binding.blur.setOnClickListener {
            binding.imageView.load(image.blur)
        }
        binding.normal.setOnClickListener {
            binding.imageView.load(image.download_url)
        }
        binding.grayscale.setOnClickListener {
            binding.imageView.load(image.grayscale)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}