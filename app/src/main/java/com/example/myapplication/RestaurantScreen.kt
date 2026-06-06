package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.clickable


data class PlaceItem(
    val rank: Int,
    val name: String,
    val rating: Double,
    val distance: String,
    val category: String,
    val imageRes: Int
)

@Composable
fun RestaurantScreen(
    title: String,
    onBack: () -> Unit,
    onPlaceClick: (PlaceItem) -> Unit
){

    val places = listOf(

        PlaceItem(
            rank = 1,
            name = "정왕 참치마을",
            rating = 4.9,
            distance = "0.3km",
            category = "횟집",
            imageRes = R.drawable.mapick_logo
        ),

        PlaceItem(
            rank = 2,
            name = "교동 순대국밥",
            rating = 4.7,
            distance = "0.5km",
            category = "한식",
            imageRes = R.drawable.mapick_logo
        ),

        PlaceItem(
            rank = 3,
            name = "커피메이트 정왕점",
            rating = 4.6,
            distance = "0.6km",
            category = "카페",
            imageRes = R.drawable.mapick_logo
        ),

        PlaceItem(
            rank = 4,
            name = "피자스쿨 정왕점",
            rating = 4.3,
            distance = "1.0km",
            category = "양식",
            imageRes = R.drawable.mapick_logo
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
        ) {

            IconButton(
                onClick = { onBack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF9B7BDB)
            ) {
                Text(
                    text = "전체",
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFF5F5F5)
            ) {
                Text(
                    text = "한식",
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFF5F5F5)
            ) {
                Text(
                    text = "일식",
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFF5F5F5)
            ) {
                Text(
                    text = "카페",
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(places) { place ->

                Column(
                    modifier = Modifier.clickable {
                        onPlaceClick(place)
                    }
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = place.rank.toString(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF9B7BDB),
                            modifier = Modifier.width(24.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFF5EEFF)
                        ) {

                            Image(
                                painter = painterResource(place.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp)
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
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = "⭐⭐⭐⭐",
                                        color = Color(0xFFFFB400),
                                        fontSize = 13.sp
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "☆",
                                        color = Color.LightGray,
                                        fontSize = 13.sp
                                    )

                                    Spacer(modifier = Modifier.width(6.dp))

                                    Text(
                                        text = place.rating.toString(),
                                        fontSize = 15.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFFF3F3F3)
                            ) {

                                Text(
                                    text = place.category,
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 4.dp
                                    ),
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = place.distance,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = Color(0xFFEDE4FF)
                            ) {

                                Text(
                                    text = "인기",
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 4.dp
                                    ),
                                    color = Color(0xFF9B7BDB)
                                )
                            }
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Color(0xFFEAEAEA)
                    )
                }
            }
        }

        Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 10.dp,
                bottom = 18.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomItem(
            iconRes = R.drawable.ic_home,
            title = "홈",
            selected = false,
            onClick = {}
        )

        BottomItem(
            iconRes = R.drawable.ic_history,
            title = "기록",
            selected = false,
            onClick = {}
        )

        BottomItem(
            iconRes = R.drawable.ic_favorite,
            title = "즐겨찾기",
            selected = true,
            onClick = {}
        )

        BottomItem(
            iconRes = R.drawable.ic_setting,
            title = "설정",
            selected = false,
            onClick = {}
        )
    }
    }
}