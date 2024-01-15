package com.example.movieworld.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.net.URLDecoder


@Composable
fun OpenImage(url: String){

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Transparent) {

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {


            Card(modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically),
                colors = CardDefaults.cardColors(Color.Transparent)) {

                Image(painter = rememberAsyncImagePainter(model = URLDecoder.decode(url)),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .sizeIn(minHeight = 200.dp))

            }
        }



    }


}