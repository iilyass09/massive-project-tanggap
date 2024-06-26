package com.example.massive.presentation.screen.akun

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.data.storage.SharedPreferencesManager
import com.example.massive.presentation.components.AkunBottomSheet
import com.example.massive.presentation.navigation.Screen
import com.example.massive.ui.theme.poppins

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AkunScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val keluarBottomSheetState = remember { mutableStateOf(false) }
    val userViewModel: AkunSayaViewModel = viewModel()

    LaunchedEffect(Unit) {
        userViewModel.fetchUser(context)
    }


    if (userViewModel.user != null) {
        val firstName by remember { mutableStateOf(userViewModel.user?.nama_depan ?: "") }
        val lastname by remember { mutableStateOf(userViewModel.user?.nama_belakang ?: "") }

        Scaffold(
            containerColor = Color.White,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .offset(y = 10.dp)
                            .width(150.dp)
                            .height(150.dp),
                        painter = painterResource(id = R.drawable.akun),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "$firstName $lastname",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.AkunSaya.route) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.akunsaya),
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.widthIn(10.dp))
                        Text(
                            text = "Akun Saya",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppins
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.PengaduanSaya.route) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pengaduansaya),
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.widthIn(10.dp))
                        Text(
                            text = "Pengaduan Saya",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppins
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.Pengaturan.route) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pengaturan),
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.widthIn(10.dp))
                        Text(
                            text = "Pengaturan",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppins
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.HubungiKami.route) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hubungikami),
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.widthIn(10.dp))
                        Text(
                            text = "Hubungi Kami",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppins
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { keluarBottomSheetState.value = true }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.keluar),
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.widthIn(10.dp))
                        Text(
                            text = "Keluar",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppins
                        )
                    }
                    if (keluarBottomSheetState.value) {
                        AkunBottomSheet(
                            navController = navController,
                            keluarBottomSheet = keluarBottomSheetState,
                            sharedPreferencesManager = sharedPreferencesManager
                        )
                    }
                }
            }
        )
    }
}