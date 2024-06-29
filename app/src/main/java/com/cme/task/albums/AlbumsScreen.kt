package com.cme.task.albums

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cme.domain.model.Album
import com.cme.task.R
import com.cme.task.utils.ImageHQ
import com.cme.task.utils.ResultModel
import com.cme.task.utils.components.Retry

private const val TAG = "AlbumsScreen"

@Composable
fun AlbumsScreen(
    onAlbumClick: (Album) -> Unit = {},
    viewModel: AlbumsViewModel = hiltViewModel(),
) {
    val albumsState = viewModel.albums.collectAsState().value

    when (albumsState) {
        is ResultModel.Loading -> {
            Log.e(TAG, "AlbumsScreen: Loading")
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        is ResultModel.Success -> {
            val albums: MutableList<Album> = albumsState.data ?: mutableListOf()
            AlbumsGrid(albums, viewModel)

            Log.e(TAG, "AlbumsScreen: Success")
        }

        is ResultModel.Failure -> {
            if (albumsState.code == 502 /*connection error code*/){
                Retry(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumsGrid(albums: MutableList<Album>, viewModel: AlbumsViewModel) {
    val pullToRefreshState = rememberPullToRefreshState()

    Box (
        modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 60.dp, start = 16.dp, end = 16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = "Explore Albums",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
                )
            }
            items(albums) { album ->
                AlbumItem(album)
            }
        }


        //Pull to refresh
        if (pullToRefreshState.isRefreshing){
            LaunchedEffect(true) {
                viewModel.getAlbums()
            }
        }
        val isRefreshing by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(isRefreshing) {
            if (isRefreshing){
                pullToRefreshState.startRefresh()
            }else {
                pullToRefreshState.endRefresh()
            }
        }
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun AlbumItem(album: Album) {
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Box(modifier = Modifier.fillMaxHeight()) {

                AsyncImage(
                    model = ImageHQ.getImageHQ(album.albumImage),
                    contentDescription = album.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.error_img)
                )

                //Gradient Black background
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black), startY = 200f
                            )
                        )
                )

                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            text = album.name ?: "",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp,
                            )
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = album.artistName ?: "",
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 10.sp,
                            )
                        )
                    }
                }


            }
        }
    }
}
