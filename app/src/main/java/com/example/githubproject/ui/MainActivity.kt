package com.example.githubproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.githubproject.R
import com.example.githubproject.databinding.ActivityMainBinding
import com.example.githubproject.ui.profiles.ProfilesFragment

class MainActivity : AppCompatActivity(),NavigationActivity {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setHomePage()
        }
    }

    override fun navigationTo(fragment: Fragment, withTransaction: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_layout, fragment)

        if (withTransaction) {
            transaction.addToBackStack("Transaction")
        }

        transaction.commit()

    }

    private fun setHomePage() {
        navigationTo(ProfilesFragment.newInstance())
    }
}