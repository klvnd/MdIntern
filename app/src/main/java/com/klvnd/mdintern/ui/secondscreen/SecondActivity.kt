package com.klvnd.mdintern.ui.secondscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.klvnd.mdintern.R
import com.klvnd.mdintern.databinding.ActivitySecondBinding
import com.klvnd.mdintern.ui.firstscreen.MainActivity
import com.klvnd.mdintern.ui.thirdscreen.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.white, null))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        val titleTextView = supportActionBar?.customView?.findViewById<TextView>(R.id.action_bar_title)
        titleTextView?.text = "Second Screen"

        val name = intent.getStringExtra("USER_NAME") ?: "John Doe"
        val selectedUserName = intent.getStringExtra("USER_SELECTED_NAME") ?: "Selected User Name"

        binding.tvName.text = name
        binding.tvSelectedUserName.text = selectedUserName

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
