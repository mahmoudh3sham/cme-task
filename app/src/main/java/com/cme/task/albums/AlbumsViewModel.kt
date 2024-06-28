package com.cme.task.albums

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.domain.model.Album
import com.cme.domain.usecase.GetAlbumsUseCase
import com.cme.task.utils.ErrorManager
import com.cme.task.utils.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsUseCase: GetAlbumsUseCase): ViewModel(){
    private val mTAG = "AlbumsViewModel"

    private val _albums: MutableStateFlow<ResultModel<MutableList<Album>?>> = MutableStateFlow(ResultModel.Loading(true))
    val albums: StateFlow<ResultModel<MutableList<Album>?>> = _albums

    fun getAlbums(){
        viewModelScope.launch {
            _albums.emit(ResultModel.Loading(isLoading = true))
            albumsUseCase.getAlbums()
                .catch { exception ->
                    Log.e(mTAG, "AlbumsViewModel Failure: ${exception.printStackTrace()}")
                    _albums.emit(ResultModel.Loading(isLoading = false))
                    _albums.emit(ResultModel.Failure(code = ErrorManager.getCode(throwable = exception)))
                }
                .collect{ response ->
                    _albums.emit(ResultModel.Loading(isLoading = false))
                    _albums.emit(ResultModel.Success(data = response))
                }
        }
    }


}