package com.example.movieworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieworld.movieDetails.Movie
import com.example.movieworld.movieDetails.getMovies
import com.example.movieworld.navcontrollerPackage.NavController
import com.example.movieworld.ui.theme.MovieWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieWorldTheme {
                NavController()
            }
        }
    }
}
@Composable
fun MyApp(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {

        ScaffoldWithTopAppBar(topBar = { TopBar() },
            content = { Content(navController = navController)},

            )

    }
}





@Composable
private fun Content(movie:List<Movie> = getMovies(), navController: NavController) {

    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(movie){

            MovieLis(it,navController)

        }


    }

}

@Composable
private fun MovieLis(movie: Movie, navController: NavController){


    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            val id = movie.id
            navController.navigate("DetailsScreen/${id}")

        }


        .aspectRatio(1f / (1f / 2f))// we can also use wrap content I set the hieght of image surface already
        .padding(8.dp),

        elevation = CardDefaults.cardElevation(6.dp)) {

        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){

            Surface(modifier = Modifier
                .padding(8.dp)
                .height(182.dp)
                .aspectRatio(4f / 5f),
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 4.dp
            ) {

                Image(painter = rememberAsyncImagePainter(model = movie.moviePosterUrl), contentDescription = "hello",
                    contentScale = ContentScale.FillHeight)
            }


            Column{

                Text(text = movie.id,style = MaterialTheme.typography.titleLarge)
                Row (modifier = Modifier.padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically){
                    Text(text = "${movie.movieRating}")
                    Icon(imageVector = Icons.Default.Star, contentDescription ="", modifier = Modifier.size(16.dp) )
                }

                Text(text = movie.actors, modifier = Modifier.padding(bottom = 2.dp))
                Text(text = "Release :${movie.releaseDate}", modifier = Modifier.padding(bottom = 2.dp))
            }

        }




//        Text(text = b)
//        Text(text = c)
//        Text(text = d)



    }

}



@Composable
private fun TopBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {

        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Movie World",
                style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = "")
        }

    }
}



@Composable
fun ScaffoldWithTopAppBar(topBar: @Composable ()-> Unit,
                          content : @Composable () -> Unit) {

    Scaffold(
        topBar = topBar

    ) {

        Column(modifier = Modifier.padding(it)){

            content()

        }


    }
}