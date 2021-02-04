package com.example.githubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.adapter.FavoriteUserAdapter
import com.example.githubuser.data.FavoriteUser
import com.example.githubuser.databinding.ActivityFavoriteBinding
import com.example.githubuser.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteUserAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteUserAdapter()
        adapter.notifyDataSetChanged()

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : FavoriteUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: FavoriteUser) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_STATE, data.login)
                intent.putExtra(DetailActivity.EXTRA_ID, data.id)
                intent.putExtra(DetailActivity.EXTRA_URL, data.avatar_url)
                startActivity(intent)
            }

        })

        binding.rvFavoriteUser.setHasFixedSize(true)
        binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        binding.rvFavoriteUser.adapter = adapter

        favoriteViewModel.getFavoriteUser()?.observe(this,{
            if (it != null){
                adapter.setData(it as ArrayList<FavoriteUser>)
            }
        })
    }

}