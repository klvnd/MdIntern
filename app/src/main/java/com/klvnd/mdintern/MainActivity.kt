package com.klvnd.mdintern

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.klvnd.mdintern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

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
            Toast.makeText(this, "Go to Second Screen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun palindromeCheck(text: String): Boolean {
        val sanitizedText = text
            .replace("\\s".toRegex(), "")
            .lowercase()
        return sanitizedText == sanitizedText.reversed()
    }
}
