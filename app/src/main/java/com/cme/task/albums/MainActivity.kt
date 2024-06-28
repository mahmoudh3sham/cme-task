package com.cme.task.albums

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.cme.task.R
import com.cme.task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mTAG = "MainActivity"
    private val viewModel: AlbumsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        subscribeViewModel()
        viewModel.getAlbums()

    }

    private fun subscribeViewModel() {
        lifecycleScope.launch {
            viewModel.albums.collect{
                Log.e(mTAG, "subscribeViewModel: " + it?.feed?.copyrightInfo)
                Log.e(mTAG, "subscribeViewModel: " + it?.feed?.albumsList?.size)
            }
        }
    }

}