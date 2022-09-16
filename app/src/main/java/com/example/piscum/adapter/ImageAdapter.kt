package com.example.piscum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.piscum.databinding.ImageItemLayoutBinding
import com.example.piscum.models.Image

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ImageItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffcallback = object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffcallback)
    var images: List<Image>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ImageItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        images[position].blur = images[position].download_url + "/?blur"
        images[position].grayscale = images[position].download_url + "?grayscale"
        val currentImage = images[position]

        holder.binding.apply {
            author.text = currentImage.author
            image.load(currentImage.download_url)
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }


}