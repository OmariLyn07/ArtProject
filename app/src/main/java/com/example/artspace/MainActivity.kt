package com.example.artspace

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artspace.data.DataSource
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ArtSpaceTheme {
                NavHost(navController = navController,
                    startDestination = Screen.Home.route + "/{id}"
                ){
                    //Home page composable
                    composable(
                        Screen.Home.route + "/{id}", arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                            defaultValue = 0
                        })
                    ){
                        HomePage(navController = navController)
                    }
                    //Art page composable
                    composable(
                        Screen.Artist.route + "/{id}", arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                        })
                    ){
                        ArtistPage(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ArtistPage(navController: NavController) {
    val id = navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0
    val art = DataSource.arts[id]

    /*
    TODO;
    Section A
    1. Artist Profile including image, name and info (birthplace and years alive)

    Section B
    2. Artist Bio

    Section C
    3. (optional) place the code below in the proper row or column layout to make it look like the design
     */
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = art.artistImageId),
                contentDescription = stringResource(id = art.artistInfoId),
                modifier = Modifier
                    .clip(CircleShape)
                    .border(BorderStroke(2.dp, Color.Black), CircleShape)

            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column {
                Text(
                    text = stringResource(id = art.artistId),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "(${stringResource(id = art.artistInfoId)})",
                )
            }

        }
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = stringResource(id = art.artistBioId),

        )

        Button(onClick = { navController.navigate(Screen.Home.route + "/$id") }) {
            Text(text = stringResource(id = R.string.back))
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    //Displays current artwork
    var current by remember {
        mutableIntStateOf(
            navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0
        )
    }
    val art = DataSource.arts[current]
    Scaffold( topBar = {
        CenterAlignedTopAppBar(
            title = {Text(text = stringResource(id = R.string.app_name))  },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding))
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_extra_large)))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    ) {
                    ArtWall(current, art.artworkImageId, art.descriptionId, navController)

                }
            }
            ArtDescriptor(art.titleId, art.artistId, art.yearId)
            DisplayController(current){
                current = if(it !in 0..<DataSource.arts.size) 0 else it
            }
        }
    }
}


@Composable
fun ArtWall(current: Int, artworkImageId: Int, descriptionId: Int, navController: NavController){
    //Home Page section A
    //TODO;
    /*
    1. Add image of artwork
    2. Add a click listener to navigate to the artist page
    Code to use on click event;
    navController.navigate(Screen.Artist.route + "/$artistId")
     */
    //ToBeRemoved

    Button(
        onClick = { navController.navigate(Screen.Artist.route + "/$current") },
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),

    ) {
        Image(painter = painterResource(id = artworkImageId), contentDescription = stringResource(id = descriptionId))
    }

    
}

@Composable
fun ArtDescriptor(titleId: Int, artistId: Int, yearId: Int){
    //Home Page Section B
    /*
    TODO;
    1. Add title of artwork
    2. Add artist name and year of artwork
     */
    //ToBeRemoved

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = titleId),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${stringResource(id = artistId)} (${stringResource(id = yearId)})"
        )
    }

}

@Composable
fun DisplayController(current: Int, updateCurrent: (Int) -> Unit) {
    /*
    Home Page Section C
    TODO:
    1. Add a button to navigate to the previous artwork
    2. Add a button to navigate to the next artwork
     */
    //Note:
    /*
    The buttons should be disabled if there is no previous or next artwork to navigate to
    The following code can be used to disable the button
    enabled = current != 0 (for the previous button)
    enabled = current != DataSource.arts.size - 1 (for the next button)

    Can use the following code to navigate to the previous or next
    updateCurrent(current - 1) (for previous button)
    updateCurrent(current + 1) (for next button)
     */
    //ToBeRemoved
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = { updateCurrent(current - 1) }) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Button(onClick = { updateCurrent(current + 1) }) {
            Text(text = "Next")
        }
    }
}