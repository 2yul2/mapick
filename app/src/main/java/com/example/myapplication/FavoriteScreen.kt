package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteScreen(
    onHome: () -> Unit,
    onHistory: () -> Unit,
    onSetting: () -> Unit
) {

    val favorites = FavoriteManager.favorites

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "즐겨찾기",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(24.dp)
        )

        if (favorites.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("저장된 장소가 없습니다")
            }

        } else {

            LazyColumn {

                items(favorites) { place ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFF5EEFF)
                        ) {

                            Image(
                                painter = painterResource(place.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(10.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = place.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "${place.category} · ${place.address}",
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "⭐ ${place.rating}",
                                color = Color(0xFFFFB400),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(
                            onClick = {

                                FavoriteManager.remove(place)

                            }
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.ic_heart),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }

                    HorizontalDivider()
                }
            }
        }
    }
}