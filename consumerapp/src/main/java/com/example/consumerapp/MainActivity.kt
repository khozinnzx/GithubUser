package com.example.consumerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FavoriteUserAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteUserAdapter()
        adapter.notifyDataSetChanged()

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)


        binding.rvFavoriteUser.setHasFixedSize(true)
        binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvFavoriteUser.adapter = adapter

        favoriteViewModel.setFavoriteUser(this)

        favoriteViewModel.getFavoriteUser()?.observe(this,{
            if (it != null){
                adapter.setData(it as ArrayList<Users>)
            }
        })
    }
}