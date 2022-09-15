package uz.gita.memorygameapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.data.models.CardData

class CarouselAdapter : ListAdapter<CardData,CarouselAdapter.VH>(object : DiffUtil.ItemCallback<CardData>(){
    override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean {
        return oldItem.amount == newItem.amount
    }

    override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean {
        return oldItem.imgUrl == newItem.imgUrl && oldItem.amount == newItem.amount
    }

}) {
    inner class VH(view : View) : RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById(R.id.imageView)
        fun bind(){
            val item = getItem(absoluteAdapterPosition)
            imageView.setImageResource(item.imgUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

}