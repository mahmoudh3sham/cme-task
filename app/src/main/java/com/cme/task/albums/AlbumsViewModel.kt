package com.cme.task.albums

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.data.entity.AlbumsResponse
import com.cme.domain.usecase.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsUseCase: GetAlbumsUseCase): ViewModel(){
    private val mTAG = "AlbumsViewModel"
    private val _albums: MutableStateFlow<AlbumsResponse?> = MutableStateFlow(null)
    val albums: StateFlow<AlbumsResponse?> = _albums

    fun getAlbums(){
        viewModelScope.launch {
            try {
                _albums.value = albumsUseCase.getAlbums()
                Log.e(mTAG, "subscribeViewModel: " + albumsUseCase.getAlbums().feed.copyrightInfo)
            }catch (e: Exception){
                Log.e("AlbumsViewModel", e.message.toString())
            }
        }
    }


}