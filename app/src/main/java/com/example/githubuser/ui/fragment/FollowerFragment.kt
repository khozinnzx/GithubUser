package com.example.githubuser.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.adapter.UserAdapter
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.githubuser.ui.activity.DetailActivity
import com.example.githubuser.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_follower.*

class FollowerFragment : Fragment(R.layout.fragment_follower) {

    private val TAG: String = "FollowerFragment"
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataUsername = arguments
        username = dataUsername?.getString(DetailActivity.EXTRA_STATE).toString()
        Log.d(TAG, "onViewCreated: username : ${username}")
        _binding = FragmentFollowerBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        rv_follower.setHasFixedSize(true)
        rv_follower.layoutManager = LinearLayoutManager(activity)
        rv_follower.adapter = adapter

        showLoading(true)
        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        followersViewModel.setListFollowers(username)
        followersViewModel.getListFollowers().observe(viewLifecycleOwner, {
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