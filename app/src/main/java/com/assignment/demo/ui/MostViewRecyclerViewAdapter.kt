package com.assignment.demo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.demo.R
import com.assignment.demo.databinding.MostViewedArticleItemviewBinding
import com.assignment.demo.responsentity.Result

class MostViewRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<MostViewRecyclerViewAdapter.RecyclerViewHolder>() {
    var list = mutableListOf<Result>()
    lateinit var onClickListener: View.OnClickListener

    inner class RecyclerViewHolder(val binding: MostViewedArticleItemviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = DataBindingUtil.inflate<MostViewedArticleItemviewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.most_viewed_article_itemview, parent, false
        )
        return RecyclerViewHolder(binding)
    }

    fun updateList(list: List<Result>?) {
        if(list==null)return
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var data = list.get(position)
        holder.binding.title.setText(data.title)
        holder.binding.description.setText(data.abstract)
        holder.binding.source.setText(data.source)
        holder.binding.date.setText(data.published_date)


    }

    override fun getItemCount(): Int {
        return list.size
    }
}