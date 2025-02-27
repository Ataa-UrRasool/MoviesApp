package com.example.movieapp.widgets


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()
//            .height(130.dp)
            .clickable {
                onItemClick(movie.id)
            },
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(10),
                shadowElevation = 4.dp
            ) {
//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(
                        movie.images[0] ?: "https://example.com/default-image.jpg"
                    ).transformations(RoundedCornersTransformation(16f)).build(),
                    contentDescription = "${movie.title}'s Poster",
                    modifier = Modifier.fillMaxWidth(),
                )

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Director: ${movie.title}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.labelSmall)

                AnimatedVisibility(visible = expanded) {
                    Column() {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp)
                        )
                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.labelSmall
                        )

                    }
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.DarkGray
                )
            }
        }
    }
}