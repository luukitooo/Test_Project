package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val preferenceAdapter by lazy { PreferenceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        onClickListeners()

    }

    private fun init() {
        binding.recyclerView.apply {
            adapter = preferenceAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    override fun onRestart() {
        super.onRestart()
        preferenceAdapter.setData(preferenceList.toList())
    }

    private fun onClickListeners() {
        binding.addButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddPreferenceActivity::class.java))
        }
        preferenceAdapter.onDeleteClickListener = {
            preferenceList.remove(it)
            preferenceAdapter.setData(preferenceList.toList())
        }
    }
}