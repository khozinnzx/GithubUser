package com.example.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.data.FavoriteUser
import com.example.githubuser.databinding.ItemUserBinding
import com.example.githubuser.model.Users

class FavoriteUserAdapter: RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>() {

    private val listUsers = ArrayList<FavoriteUser>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(users: ArrayList<FavoriteUser>) {
        listUsers.clear()
        listUsers.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size


    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: FavoriteUser) {
            Glide.with(itemView.context)
                .load(users.avatar_url)
                .into(binding.imageUser)
            binding.tvName.text = users.login
            binding.tvUrl.text = users.url

            binding.root.setOnClickListener { onItemClickCallback?.onItemClicked(users) }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: FavoriteUser)
    }
}