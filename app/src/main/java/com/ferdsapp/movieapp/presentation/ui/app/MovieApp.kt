package com.ferdsapp.movieapp.presentation.ui.app

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferdsapp.detail.presentation.ui.DetailMovieScreen
import com.ferdsapp.genre.ui.listGenre.GenreScreen
import com.ferdsapp.genre.ui.listMovieGenre.ListMovieGenre
import com.ferdsapp.movieapp.presentation.navigation.Screen
import com.ferdsapp.movieapp.presentation.ui.component.MovieBottomBar
import com.ferdsapp.movieapp.presentation.ui.component.MovieTopBar
import com.ferdsapp.profile.Presentation.ui.ProfileScreen
import com.ferdsapp.search.presentation.ui.SearchMovieScreen
import kotlinx.coroutines.launch

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val itemsDrawer = listOf(
        MenuItem(
            title = "Home",
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        MenuItem(
            title = "Genre",
            icon = Icons.Default.LocalOffer,
            screen = Screen.Genre
        )

    )

    val selectedItem = remember {
        mutableStateOf(
            itemsDrawer[0]
        )
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MovieTopBar(
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            )
        },
        bottomBar = {
            MovieBottomBar(navController)
        }
    ) { innerPadding ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(innerPadding),
            drawerState = drawerState,
            scrimColor = Color.Black.copy(alpha = 0.6f),
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color(0xFF111111),
                    drawerContentColor = Color.White
                ) {
                    itemsDrawer.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null, tint = Color.White) },
                            label = { Text(text = item.title, color = Color.White) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    when(item.screen){
                                        Screen.Genre -> {
                                            // reset Genre biar selalu tampil list genre
                                            navController.navigate(Screen.Genre.route) {
                                                launchSingleTop = true
                                                popUpTo(Screen.Genre.route) { inclusive = false }
                                                restoreState = false
                                            }
                                        }
                                        Screen.Home -> {
                                            navController.navigate(Screen.Home.route) {
                                                launchSingleTop = true
                                                popUpTo(Screen.Home.route) { inclusive = false }
                                                restoreState = false
                                            }
                                        }
                                        else -> {
                                            navController.navigate(item.screen.route) {
                                                launchSingleTop = true
                                            }
                                        }
                                    }
                                }
                                selectedItem.value = item
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color(0xFF7C3E00)
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.White.copy(alpha = 0.12f)
                        )
                    }
                }
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier
                ){
                    composable(Screen.Home.route){
                        HomeScreen(
                            navigateToDetail = { movieId ->
                                navController.navigate(Screen.Detail.createRoute(movieId = movieId))
                            }
                        )
                    }
                    composable(Screen.Genre.route) {
                        GenreScreen(
                            navigateToListMovie = { with_genres, genres_name ->
                                navController.navigate(Screen.ListMovieGenre.createRoute(with_genres, genres_name))
                            }
                        )
                    }
                    composable(
                        route = Screen.ListMovieGenre.route,
                        arguments = listOf(
                            navArgument("with_genres"){type = NavType.StringType},
                            navArgument("genres_name"){type = NavType.StringType}
                        )
                    ){
                        val with_genres = it.arguments?.getString("with_genres") ?: "12"
                        val genres_name = it.arguments?.getString("genres_name") ?: ""
                        ListMovieGenre(
                            with_genres = with_genres,
                            genres_name = genres_name,
                            navigateToDetail = { movieId ->
                                navController.navigate(Screen.Detail.createRoute(movieId = movieId))
                            }
                        )
                    }
                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(
                            navArgument("movieId"){type = NavType.IntType}
                        )
                    ){
                        val movieId = it.arguments?.getInt("movieId") ?: 1
                        DetailMovieScreen(
                            movieId = movieId
                        )
                    }
                    composable(
                        route = Screen.Profile.route
                    ){
                        ProfileScreen()
                    }
                    composable(
                        route = Screen.Search.route
                    ) {
                        SearchMovieScreen()
                    }
                }
            }
        )
    }
}