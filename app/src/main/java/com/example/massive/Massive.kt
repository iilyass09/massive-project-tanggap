package com.example.massive

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.massive.ui.screen.akun.AkunScreen
import com.example.massive.ui.screen.berita.BeritaScreen
import com.example.massive.ui.screen.berita.DetailBerita
import com.example.massive.ui.screen.chatbot.Chatbot2
import com.example.massive.ui.screen.chatbot.ChatbotScreen
import com.example.massive.ui.screen.community.CommunityScreen
import com.example.massive.ui.screen.home.HomeScreen
import com.example.massive.ui.screen.navigation.NavigationItem
import com.example.massive.ui.screen.navigation.Screen
import com.example.massive.ui.screen.pengaduan.Panduan
import com.example.massive.ui.screen.pengaduan.PengaduanContentStep0
import com.example.massive.ui.screen.pengaduan.PengaduanContentStep1
import com.example.massive.ui.screen.pengaduan.PengaduanContentStep2
import com.example.massive.ui.screen.pengaduan.PengaduanScreen
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun Massive(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        floatingActionButton = {
            val currentRoute = currentRoute(navController = navController)
            if (currentRoute !in listOf(
                    Screen.Pengaduan.route,
                    Screen.Panduan.route,
                    Screen.DetailBerita.route + "/{beritaId}",
                    Screen.Chatbot.route,
                    Screen.Chatbot2.route,)
                ) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Chatbot.route) },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp),
                    containerColor = Biru
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.iconbot2),
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Color.White,
        topBar = {
            val currentRoute = currentRoute(navController = navController)
            if (currentRoute != Screen.Home.route && currentRoute != Screen.Chatbot2.route)
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.White),
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            if (currentRoute in listOf(
                                    Screen.Panduan.route,
                                    Screen.Pengaduan.route,
                                    Screen.DetailBerita.route + "/{beritaId}",
                                    Screen.Chatbot.route)
                            ) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        modifier = Modifier.padding(top = 15.dp).offset(x = (-5).dp),
                                        imageVector = Icons.Default.ArrowBackIosNew,
                                        contentDescription = null,
                                    )
                                }
                            }
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth().offset(x = (-10).dp, y = (7.dp)),
                            text = getTitleForRoute(route = currentRoute),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = poppins,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            )
        },
        bottomBar = {
            val currentRoute = currentRoute(navController = navController)
            if (currentRoute !in listOf(
                    Screen.Pengaduan.route,
                    Screen.Chatbot.route,
                    Screen.Chatbot2.route,
                    Screen.Panduan.route,
                    Screen.DetailBerita.route + "/{beritaId}")
                ) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { contentPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(Screen.Home.route){ HomeScreen(navController) }
            composable(Screen.Community.route){ CommunityScreen(navController) }
            composable(Screen.Pengaduan.route) { PengaduanScreen(navController) }
            composable(Screen.Berita.route){ BeritaScreen(navController) }
            composable(Screen.Akun.route){ AkunScreen(navController) }
            composable(Screen.Chatbot.route){ ChatbotScreen(navController) }
            composable(Screen.Chatbot2.route){ Chatbot2(navController) }
            composable(Screen.Panduan.route){ Panduan(navController) }
            composable(
                Screen.DetailBerita.route + "/{beritaId}",
                arguments = listOf(navArgument("beritaId") { type = NavType.IntType})
            ) { navBackStackEntry ->
                DetailBerita(navController = navController, beritasId = navBackStackEntry.arguments?.getInt("beritaId"))
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                iconClick = R.drawable.iconhomeclick,
                iconUnclick = R.drawable.home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_komunitas),
                iconClick = R.drawable.iconkomunitasclick,
                iconUnclick = R.drawable.iconkomunitas,
                screen = Screen.Community
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_pengaduan),
                iconClick = R.drawable.iconpengaduanclick,
                iconUnclick = R.drawable.iconpengaduan,
                screen = Screen.Pengaduan
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_berita),
                iconClick = R.drawable.iconberitaclick,
                iconUnclick = R.drawable.iconberita,
                screen = Screen.Berita
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_akun),
                iconClick = R.drawable.iconakunclick,
                iconUnclick = R.drawable.iconakun,
                screen = Screen.Akun
            ),
        )

        navigationItems.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                ) {
                    val icon = if (currentRoute == item.screen.route) item.iconClick else item.iconUnclick
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.screen.route) Biru else Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    modifier = Modifier.offset(y = (-5).dp),
                    text = item.title,
                    fontFamily = poppins,
                    fontSize = 11.sp,
                    color = if (currentRoute == item.screen.route) Biru else Color.Black
                )
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun getTitleForRoute(route: String?): String {
    return when (route) {
        Screen.Community.route -> stringResource(id = R.string.menu_komunitas)
        Screen.Pengaduan.route -> stringResource(id = R.string.menu_pengaduan)
        Screen.Berita.route -> stringResource(id = R.string.menu_berita)
        Screen.Akun.route -> stringResource(id = R.string.menu_akun)
        Screen.Chatbot.route -> stringResource(id = R.string.menu_chatbot)
        Screen.Panduan.route -> stringResource(id = R.string.menu_panduan)
        else -> {
            if (route?.startsWith(Screen.DetailBerita.route) == true) {
                stringResource(id = R.string.menu_berita)
            } else {
                stringResource(id = R.string.app_name)
            }
        }
    }
}
