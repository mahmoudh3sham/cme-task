package com.cme.task.details

import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cme.domain.model.Album
import com.cme.domain.model.Genre
import com.cme.task.R
import com.cme.task.theme.Pink
import com.cme.task.theme.Purple80
import com.cme.task.utils.ImageHQ

@Composable
fun AlbumDetailsScreen(album: Album?) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    )
    {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)) {
                AsyncImage(
                    model = ImageHQ.getImageHQ(album?.albumImage),
                    contentDescription = album?.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
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

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            text = album?.name ?: "",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 30.sp,
                            )
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = album?.artistName ?: "",
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                            )
                        )
                    }
                }
            }

            val genres: List<Genre> = album?.genre ?: listOf()
            LazyRow(modifier = Modifier
                .padding(8.dp)) {
                itemsIndexed(
                    items = genres
                ){ _, genre ->
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            //val intent = Intent(Intent.ACTION_VIEW, Uri.parse(genre.genreUrl))
                            //context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = genre.genreName ?: "")
                    }
                }
            }

            Row (modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "Release Date: ",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                    )
                )
                Text(
                    text = album?.releaseDate ?: "",
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), contentAlignment = Alignment.BottomCenter) {
                Text(
                    text = album?.copyrightInfo ?: "",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                    )
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), contentAlignment = Alignment.BottomEnd) {
                Button(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(end = 20.dp),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(album?.albumUrl))
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Pink,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "view on iTunes Store")
                }
            }


        }


    }
}