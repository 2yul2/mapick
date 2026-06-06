package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlaceDetailScreen(
    onBack: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(Color(0xFFEDE4FF))
        ) {
            IconButton(
                onClick = {
                    onBack()
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = 16.dp,
                        top = 40.dp
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.Center)
                    .background(
                        Color(0xFFDCCEFF),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.mapick_logo),
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "정왕 참치마을",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        onFavoriteClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_heart),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                repeat(4) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_star_empty),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "4.9 (리뷰 128개)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "횟집 · 일식 · 정왕동",
                color = Color.Gray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(30.dp))

            DetailRow(
                icon = R.drawable.ic_location,
                text = "경기도 시흥시 정왕동 123-4"
            )

            Spacer(modifier = Modifier.height(20.dp))

            DetailRow(
                icon = R.drawable.ic_clock,
                text = "11:00 - 22:00"
            )

            Spacer(modifier = Modifier.height(20.dp))

            DetailRow(
                icon = R.drawable.ic_phone,
                text = "031-000-0000"
            )

            Spacer(modifier = Modifier.height(20.dp))

            DetailRow(
                icon = R.drawable.ic_send,
                text = "중간지점에서 도보 5분 (0.3km)"
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                ActionButton(
                    icon = R.drawable.ic_map,
                    text = "길 찾기"
                )

                ActionButton(
                    icon = R.drawable.ic_phone,
                    text = "전화"
                )

                ActionButton(
                    icon = R.drawable.ic_share,
                    text = "공유",
                    filled = true
                )
            }
        }
    }
}
@Composable
fun DetailRow(
    icon: Int,
    text: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}
@Composable
fun ActionButton(
    icon: Int,
    text: String,
    filled: Boolean = false
) {

    Button(
        onClick = {},
        modifier = Modifier.width(130.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (filled)
                    Color(0xFF9B7BDB)
                else
                    Color.White
        ),
        shape = RoundedCornerShape(18.dp)
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint =
                if (filled)
                    Color.White
                else
                    Color(0xFF9B7BDB)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color =
                if (filled)
                    Color.White
                else
                    Color(0xFF9B7BDB)
        )
    }
}