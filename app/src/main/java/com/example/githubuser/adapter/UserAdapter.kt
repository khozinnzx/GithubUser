package com.example.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemUserBinding
import com.example.githubuser.model.Users

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUsers = ArrayList<Users>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(users: ArrayList<Users>) {
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
        fun bind(users: Users) {
            Glide.with(itemView.context)
                .load(users.avatar_url)
                .into(binding.imageUser)
            binding.tvName.text = users.login
            binding.tvUrl.text = users.url

            binding.root.setOnClickListener { onItemClickCallback?.onItemClicked(users) }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Users)
    }

}