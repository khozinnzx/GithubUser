package com.example.githubuser.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.adapter.SectionPagerAdapter
import com.example.githubuser.databinding.ActivityDetailBinding
import com.example.githubuser.viewmodel.DetailActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailActivityViewModel

    companion object {
        internal val TAG = DetailActivity::class.java.simpleName
        const val EXTRA_STATE = "extra_state"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR_URL = "extra_avatar_url"
        const val EXTRA_URL = "extra_url"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_STATE)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL)
        val url = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_STATE, username)

        detailViewModel = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)

        if (username != null) {
            detailViewModel.setUserDetail(username)
        }

        detailViewModel.getUserDetail().observe(this, {
            if (it != null) {
                Glide.with(this)
                    .load(it.avatar_url)
                    .centerCrop()
                    .into(binding.imgUser)
                binding.tvName.text = it.name
                binding.tvUsername.text = it.login
                binding.tvFollower.text = "  ${it.followers} " + getString(R.string.Followers)
                binding.tvFollowing.text = "  ${it.following} " + getString(R.string.Following)

            }
        })

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.tbFavorite.isChecked = true
                        _isChecked = true

                    } else {
                        binding.tbFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.tbFavorite.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
               detailViewModel.addToFavorite(username!!, id, avatarUrl!!, url!!)

            } else {
               detailViewModel.deleteFromFavorite(id)
            }
            binding.tbFavorite.isChecked = _isChecked
        }


        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabsLayout.setupWithViewPager(binding.viewPager)

    }
}