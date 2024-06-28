package com.cme.task.albums

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import com.cme.task.R
import com.cme.task.databinding.ActivityMainBinding
import com.cme.task.utils.ResultModel
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.albums.collect{ result ->
                    when (result) {
                        is ResultModel.Loading -> {
                            Log.e(mTAG, "subscribeViewModel: IsLoading = " + result.isLoading)
                        }
                        is ResultModel.Success -> {
                            if (!result.data.isNullOrEmpty()){
                                Log.e(mTAG, "subscribeViewModel: Success Count: " + result.data.size)
                                Log.e(mTAG, "subscribeViewModel: Success " + result.data[0].copyrightInfo)
                                binding.hello.text = result.data.size.toString()
                            }
                        }
                        is ResultModel.Failure -> {
                            Log.e(mTAG, "subscribeViewModel: Failure " + result.code)
                            if (result.code == 502){
                                //TODO: handle retry
                            }
                        }
                    }
                }
            }
        }
    }

}