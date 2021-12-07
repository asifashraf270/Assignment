package com.shisheo.demo.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shisheo.demo.R
import com.shisheo.demo.databinding.RestaurantItemviewBinding
import com.shisheo.demo.responsentity.restaurantlist.RestaurantResponseModelItem
import com.shisheo.demo.utils.AppLogger

class RestaurantRecyclerView(val context: Context) :
    RecyclerView.Adapter<RestaurantRecyclerView.RecyclerViewHolder>() {
    var restaurantList = mutableListOf<RestaurantResponseModelItem>()
    lateinit var onClickListener: View.OnClickListener

    inner class RecyclerViewHolder(val binding: RestaurantItemviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = DataBindingUtil.inflate<RestaurantItemviewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.restaurant_itemview, parent, false
        )
        return RecyclerViewHolder(binding)
    }

    fun updateList(list: List<RestaurantResponseModelItem>) {
        this.restaurantList.clear()
        this.restaurantList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        var restaurant = restaurantList.get(position)

        if (!payloads.isEmpty()) {
            if (payloads.get(0).equals("updateRating")) {
                holder.binding.rateBar.rating = restaurant.ratingStar.toFloat()

            }
        } else
            super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var restaurant = restaurantList.get(position)
        holder.binding.restaurantName.setText(restaurant.name)
        holder.binding.resturantDescription.setText(restaurant.description)
        holder.binding.sale.setText(restaurant.offer)
        Glide.with(context).load(restaurant.image_url)
            .placeholder(context.resources.getDrawable(R.drawable.resplaceholder))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(holder.binding.image)
        holder.binding.rateBar.rating = restaurant.ratingStar.toFloat()
        holder.binding.rootView.setTag(position)
        holder.binding.rootView.setOnClickListener(onClickListener)

    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }
}