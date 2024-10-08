package stu.cn.ua.lab_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stu.cn.ua.lab_2.databinding.ItemThumbnailBinding

class GalleryAdapter(
    private val images: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = images[position]
        Picasso.get().load(imageUrl).into(holder.binding.thumbnail)
        holder.binding.root.setOnClickListener { onClick(imageUrl) }
    }

    override fun getItemCount(): Int = images.size

    class ImageViewHolder(val binding: ItemThumbnailBinding) : RecyclerView.ViewHolder(binding.root)
}
