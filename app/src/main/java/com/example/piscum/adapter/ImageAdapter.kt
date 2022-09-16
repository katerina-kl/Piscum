package com.example.piscum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.piscum.R
import com.example.piscum.fragments.HomeFeedFragmentDirections
import com.example.piscum.models.Image

class ImageAdapter(private val images: ArrayList<Image>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val author: TextView = itemView.findViewById(R.id.author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.image_item_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ImageAdapter.ViewHolder, position: Int) {

        val image: Image = images[position]
        image.blur = images[position].download_url + "/?blur"
        image.grayscale = images[position].download_url + "?grayscale"
        val textView = viewHolder.author
        textView.text = image.author
        val imageView = viewHolder.image
        imageView.load(image.download_url)
        viewHolder.itemView.setOnClickListener { mView ->
            val direction = HomeFeedFragmentDirections.actionHomeFeedFragmentToDetailFragment(image)
            mView.findNavController().navigate(direction)
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }
}