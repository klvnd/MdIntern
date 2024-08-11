package com.klvnd.mdintern.ui.thirdscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klvnd.mdintern.R
import com.klvnd.mdintern.data.response.DataItem
import com.klvnd.mdintern.databinding.ActivityThirdBinding
import com.klvnd.mdintern.ui.secondscreen.SecondActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        userAdapter = UserAdapter(emptyList(), object : UserAdapter.OnItemClickListener {
            override fun onItemClick(user: DataItem) {
                val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                intent.putExtra("USER_SELECTED_ID", user.id)
                intent.putExtra("USER_SELECTED_NAME", "${user.firstName} ${user.lastName}")
                startActivity(intent)
            }
        })

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = userAdapter

        userViewModel.users.observe(this, Observer { users ->
            userAdapter.updateData(users)
        })

        binding.swipeRefresh.setOnRefreshListener {
            userViewModel.refreshUsers()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupActionBar() {
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.custom_action_bar)
            setBackgroundDrawable(resources.getDrawable(R.color.white, null))
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)

            val titleTextView = customView?.findViewById<TextView>(R.id.action_bar_title)
            titleTextView?.text = "Third Screen"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
