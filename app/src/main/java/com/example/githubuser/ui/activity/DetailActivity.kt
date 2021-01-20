package com.example.githubuser.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubuser.R
import com.example.githubuser.adapter.SectionPagerAdapter
import com.example.githubuser.databinding.ActivityDetailBinding
import com.example.githubuser.databinding.ActivityMainBinding
import com.example.githubuser.model.Users
import com.example.githubuser.viewmodel.DetailActivityViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailActivityViewModel

    companion object{
        internal val TAG = DetailActivity::class.java.simpleName
        const val EXTRA_STATE = "extra_state"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_STATE)
        val bundle = Bundle()
        bundle.putString(EXTRA_STATE, username)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailActivityViewModel::class.java)
        if (username != null) {
            detailViewModel.setUserDetail(username)
        }

        detailViewModel.getUserDetail().observe(this,{
            if (it != null){
                Glide.with(this)
                    .load(it.avatar_url)
                    .centerCrop()
                    .into(img_user)
                tv_name.text = it.name
                tv_username.text = it.login
                tv_follower.text = "  ${it.followers} "+ getString(R.string.Followers)
                tv_Following.text = "  ${it.following} "+ getString(R.string.Following)

            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabsLayout.setupWithViewPager(binding.viewPager)

    }
}