package com.ferdsapp.movieapp.screen.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ferdsapp.home.presentation.ui.HomeScreen
import com.ferdsapp.movieapp.navigation.Screen
import com.ferdsapp.movieapp.screen.component.MovieTopBar
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
            icon = Icons.Default.Star,
            screen = Screen.Genre
        )

    )

    val selectedItem = remember {
        mutableStateOf(
            itemsDrawer[0]
        )
    }

    Scaffold(
        topBar = {
            MovieTopBar(
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isClosed){
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(innerPadding),
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    itemsDrawer.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(text = item.title) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(item.screen.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                    }
                                }
                                selectedItem.value = item
                            },
                            modifier = Modifier.padding(horizontal = 12.dp)
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
                        HomeScreen()
                    }
                }
            }
        )
    }
}