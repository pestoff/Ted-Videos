package ru.pestoff.tedvideos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.pestoff.tedvideos.databinding.ItemViewBinding
import ru.pestoff.tedvideos.model.Item
import ru.pestoff.tedvideos.util.StringUtil

class VideoAdapter(private val onClickListener: OnClickListener)
    : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    interface OnClickListener {
        fun onClick(item: Item)
    }

    var videos: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        return VideoHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    inner class VideoHolder(var itemViewBinding: ItemViewBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(item: Item) {

            itemViewBinding.root.setOnClickListener {
                onClickListener.onClick(item)
            }

            Glide.with(itemViewBinding.root)
                .load(item.image.url)
                .transform(MultiTransformation(
                    RoundedCorners(40),
                    CenterCrop()))
                .into(itemViewBinding.imageView)

            itemViewBinding.authorText.text = StringUtil.getAuthorFromTitle(item.title)
            itemViewBinding.themeText.text = StringUtil.getThemeFromTitle(item.title)

            itemViewBinding.durationText.text = item.duration

        }
    }


}