package com.example.movieworld

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieworld.movieDetails.getMovies
import java.net.URLEncoder


@Composable
fun MovieFullInfo(id: String,navController: NavController){

    val newMovie = getMovies().filter { movie ->
        movie.id == id
    }


    Surface (modifier = Modifier.fillMaxSize(),
        color = Color.Black){


        Column(modifier = Modifier.fillMaxSize()) {

               UpperMovieHead(

                   newMovie[0].id,
                   newMovie[0].actors,
                   newMovie[0].directorName,
                   newMovie[0].writerName,
                   newMovie[0].releaseDate,
                   newMovie[0].movieRating,
                   newMovie[0].moviePosterUrl
               )

            BottomLazyCol(newMovie[0].story,
                newMovie[0].otherPoster,
                navController)








        }

    }





}

@Composable
private fun BottomLazyCol( story :String,otherPoster: List<String>,navController: NavController) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("About Movie: ")
                }
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append(story)
                }

            }, modifier = Modifier.padding(12.dp))
        }


        items(otherPoster) {

            BottomImage(it,navController)

        }

    }
}

@Composable
private fun BottomImage(url: String,navController: NavController) {
    Card(modifier = Modifier
        .padding(4.dp)
        .wrapContentHeight(Alignment.CenterVertically)) {

        Image(painter = rememberAsyncImagePainter(model = url),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .sizeIn(minHeight = 200.dp)
                .clickable {
                    val urlDecoded = URLEncoder.encode(url)

                    navController.navigate("OpenImage/${urlDecoded}")

                }
        )

    }
}





@Composable
private fun UpperMovieHead(id: String,
                           actors: String,
                           directorName: String,
                           writerName: String,
                           releaseDate: String,
                           movieRating: Double,
                           moviePosterUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .height(180.dp)
                .aspectRatio(4f / 5f),
            shape = RoundedCornerShape(bottomEnd = 16.dp),
            color = Color.White
        ) {

            Image(painter = rememberAsyncImagePainter(model = moviePosterUrl), contentDescription = "sd",
                contentScale = ContentScale.FillHeight)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {

            Text(text = id, style = MaterialTheme.typography.titleLarge, color = Color.White)
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "$movieRating", color = Color.White)
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp), tint = Color.White
                )
            }

            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White )){
                    append("Actors: ")
                }

                withStyle(style = SpanStyle(color = Color.Green)){
                    append(actors)
                }
            }, modifier = Modifier.padding(bottom = 2.dp))





            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White )){
                    append("Directors: ")
                }

                withStyle(style = SpanStyle(color = Color.Green)){
                    append(directorName)
                }
            }, modifier = Modifier.padding(bottom = 2.dp))


            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White )){
                    append("Writers: ")
                }

                withStyle(style = SpanStyle(color = Color.Green)){
                    append(writerName)
                }
            }, modifier = Modifier.padding(bottom = 2.dp))



            Text(text = "Release Date : $releaseDate", modifier = Modifier.padding(bottom = 2.dp), color = Color.Red)
        }

    }
}