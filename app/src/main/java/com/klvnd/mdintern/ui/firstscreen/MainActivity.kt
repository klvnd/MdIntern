package com.klvnd.mdintern.ui.firstscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.klvnd.mdintern.ui.secondscreen.SecondActivity
import com.klvnd.mdintern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnCheck.setOnClickListener {
            val inputText = binding.etPalindrome.text.toString()
            val result = if (palindromeCheck(inputText)) "isPalindrome" else "notPalindrome"

            AlertDialog.Builder(this)
                .setTitle("Palindrome Check")
                .setMessage(result)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.btnNext.setOnClickListener {
            val userName = binding.etName.text.toString().ifBlank { "John Doe" }
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
        }
    }

    private fun palindromeCheck(text: String): Boolean {
        val sanitizedText = text
            .replace("\\s".toRegex(), "")
            .lowercase()
        return sanitizedText == sanitizedText.reversed()
    }
}
