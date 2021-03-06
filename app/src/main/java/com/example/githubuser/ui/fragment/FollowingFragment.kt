package com.example.githubuser.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.adapter.UserAdapter
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.ui.activity.DetailActivity
import com.example.githubuser.viewmodel.FollowingViewModel


class FollowingFragment : Fragment(R.layout.fragment_following) {

    private val TAG: String = "FollowingFragment"
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var followingViewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataUsername = arguments
        username = dataUsername?.getString(DetailActivity.EXTRA_STATE).toString()
        Log.d(TAG, "onViewCreated: username : ${username}")
        _binding = FragmentFollowingBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.rvFollowing.setHasFixedSize(true)
        binding.rvFollowing.layoutManager = LinearLayoutManager(activity)
        binding.rvFollowing.adapter = adapter

        showLoading(true)
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java
        )
        followingViewModel.setListFollowing(username)
        followingViewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setData(it)
                Log.d(TAG, "onViewCreated: tes nama: ${it.toString()}")
                showLoading(false)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}