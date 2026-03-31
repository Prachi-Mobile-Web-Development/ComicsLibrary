package com.example.comicslibrary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comicslibrary.ui.theme.ComicsLibraryTheme
import com.example.comicslibrary.view.CharacterDetailsScreen
import com.example.comicslibrary.view.CollectionScreen
import com.example.comicslibrary.view.LibraryScreen
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination(val route: String){
    object Library: Destination("library")
    object Collection: Destination("collection")
    object CharacterDetail: Destination("characterDetail")

}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComicsLibraryTheme {
                Surface(modifier = Modifier.fillMaxSize(),color= MaterialTheme.colorScheme.background) {
                    val navController= rememberNavController()

                    CharacterScaffold(navController = navController)
                }


            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterScaffold(navController: NavHostController){
     Scaffold(bottomBar = {}) {
         NavHost(
             navController = navController,
             startDestination = Destination.Library.route
         ) {
             composable(Destination.Collection.route) {
                 LibraryScreen()

             }
             composable(Destination.Library.route) {
                 CollectionScreen()


             }
             composable(Destination.CharacterDetail.route ) {
                 CharacterDetailsScreen()
             }

         }
     }
}

