package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAddPreferenceBinding

class AddPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListeners()

    }

    private fun onClickListeners() {
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val isSwitch = binding.isSwitch.isChecked
            val preference = Preference(title, isSwitch)
            preferenceList.add(preference)
            finish()
        }
    }

}