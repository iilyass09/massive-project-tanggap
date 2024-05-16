package com.example.massive.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.model.Berita
import com.example.massive.data.DataBerita
import com.example.massive.data.DataKomunitas
import com.example.massive.data.SharedPreferencesManager
import com.example.massive.model.Komunitas
import com.example.massive.ui.navigation.Screen
import com.example.massive.ui.theme.Abu
import com.example.massive.ui.theme.Biru
import com.example.massive.ui.theme.poppins

@Composable
fun HomeScreen(navController: NavController) {
    val komunitass: List<Komunitas> = DataKomunitas.ListKomunitas
    val beritas: List<Berita> = DataBerita.ListBerita
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val name = sharedPreferencesManager.name ?: ""

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            HomeTopBar(name = name)
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy((-15).dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Banner()
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    items(komunitass.take(3)) { komunitas ->
                        KomunitasItem(komunitas = komunitas) { komunitasId ->
                            navController.navigate(Screen.DetailCommunity.route + "/$komunitasId")
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                            text = "Berita Terkini",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                        )
                        Text(
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                            text = "Temukan berita terupdate tentang\n" + "Kota Bandung",
                            color = Abu,
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = poppins
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    items(beritas.filter { it.id in 1..3 }) { berita ->
                        BeritaItem(berita = berita) { beritaId ->
                            navController.navigate(Screen.DetailBerita.route + "/$beritaId")
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(name : String) {
    TopAppBar(
        title = {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = poppins
            )
        },
        navigationIcon = {
            Box{
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(7.dp)
                        .size(40.dp)
                        .clickable { }
                )
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Image(
                    painter = painterResource(id = R.drawable.notif),
                    contentDescription = "Notif",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(23.dp)
                )
            }
        }
    )
}

@Composable
fun Banner() {
    Column {
        Surface(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 5.dp)
                .fillMaxWidth(),
            shadowElevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            color = Biru
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Biru)
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        text = "Sampaikan suara Anda\n" +
                                "pada kami.",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        text = "Laporan Anda membawa perbaikan.Mari\n" +
                                "bersama wujudkan perubahan yang\n" +
                                "lebih baik untuk masyarakat.",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppins,
                        lineHeight = 15.sp,
                        textAlign = TextAlign.Start
                    )
                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.iconcardhome),
                        contentDescription = null,
                        modifier = Modifier
                            .offset(x = 118.dp)
                            .scale(1.4f)
                    )
                }
            }
        }
        Text(
            modifier = Modifier.offset(x = 10.dp),
            text = "Komunitas",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppins
        )
        Text(
            modifier = Modifier.offset(x = 10.dp, y = 3.dp),
            text = "Temukan pengaduan yang sedang\n" +
                    "ramai dibicarakan",
            color = Abu,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppins
        )
    }
}

@Composable
fun KomunitasItem(
    komunitas: Komunitas,
    onItemClicked : (Int) -> Unit
) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .padding(20.dp)
            .clickable { onItemClicked(komunitas.id) }
            .height(
                if (komunitas.bukti != null) {
                    380.dp
                } else {
                    230.dp
                }
            )
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = komunitas.profil),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = komunitas.nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = poppins
                    )
                    Text(
                        text = "20 menit yang lalu",
                        color = Abu,
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            if (komunitas.bukti != null) {
                Image(
                    painter = painterResource(id = komunitas.bukti),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(140.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = komunitas.uraian,
                fontSize = 14.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 10.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.comment),
                            contentDescription = null
                        )
                        Text(text = "20", modifier = Modifier.padding(start = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = null
                        )
                        Text(text = "10", modifier = Modifier.padding(start = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Row(Modifier.padding(end = 5.dp),verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                        Text(text = "2", modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun BeritaItem(
    berita: Berita,
    onItemClicked : (Int) -> Unit
) {
    if (berita.id in 1..3) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .padding(20.dp)
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(berita.id) }
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = berita.judul,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = poppins,
                    lineHeight = 18.sp
                )
                Text(
                    text = berita.waktu,
                    color = Abu,
                    fontSize = 10.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = berita.foto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}