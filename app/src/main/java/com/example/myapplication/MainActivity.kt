package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.kakao.vectormap.KakaoMapSdk
import java.security.MessageDigest

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 키 해시 확인용 (등록 후 삭제해도 됨)
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                val info = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNING_CERTIFICATES
                )
                info.signingInfo?.apkContentsSigners?.forEach { signature ->
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                    Log.d("KAKAO_KEY", keyHash)
                }
            } else {
                @Suppress("DEPRECATION")
                val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                @Suppress("DEPRECATION")
                info.signatures?.forEach { signature ->
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                    Log.d("KAKAO_KEY", keyHash)
                }
            }
        } catch (e: Exception) {
            Log.d("KAKAO_KEY", "오류: ${e.message}")
        }

        KakaoMapSdk.init(this, "224d7660a967055bf1291d5fc07aca22")

        setContent {

            var currentScreen by remember { mutableStateOf("splash") }
            var selectedPlace by remember {
                mutableStateOf<PlaceItem?>(null)
            }

            // ✅ 여러 출발지 결과 리스트로 변경
            var transitResults by remember { mutableStateOf<List<TransitResult?>>(emptyList()) }

            when (currentScreen) {

                "splash" -> {
                    SplashScreen(
                        onFinish = {
                            currentScreen = "onboarding"
                        }
                    )
                }
                "history" -> {

                    HistoryScreen(
                        onHome = {
                            currentScreen = "main"
                        },
                        onFavorite = {
                            currentScreen = "favorite"
                        },
                        onSetting = {
                            currentScreen = "setting"
                        }
                    )
                }

                "favorite" -> {

                    FavoriteScreen(
                        onHome = {
                            currentScreen = "main"
                        },
                        onHistory = {
                            currentScreen = "history"
                        },
                        onSetting = {
                            currentScreen = "setting"
                        }
                    )
                }

                "setting" -> {

                    SettingScreen(
                        onHome = {
                            currentScreen = "main"
                        },
                        onHistory = {
                            currentScreen = "history"
                        },
                        onFavorite = {
                            currentScreen = "favorite"
                        }
                    )
                }
                "onboarding" -> {
                    OnboardingScreen(
                        onStart = {
                            currentScreen = "main"
                        }
                    )
                }

                "main" -> {
                    MyScreen(

                        onSearchClick = {
                            currentScreen = "candidate"
                        },

                        onNavigate = {
                            currentScreen = it
                        }
                    )
                }
                "restaurant" -> {

                    RestaurantScreen(
                        title = "주변 맛집",

                        onBack = {
                            currentScreen = "result"
                        },

                        onPlaceClick = { place ->

                            selectedPlace = place

                            currentScreen = "detail"
                        }
                    )
                }

                "cafe" -> {

                    RestaurantScreen(
                        title = "주변 카페",

                        onBack = {
                            currentScreen = "result"
                        },

                        onPlaceClick = { place ->

                            selectedPlace = place

                            currentScreen = "detail"
                        }
                    )
                }

                "play" -> {

                    RestaurantScreen(
                        title = "주변 놀거리",

                        onBack = {
                            currentScreen = "result"
                        },

                        onPlaceClick = { place ->

                            selectedPlace = place

                            currentScreen = "detail"
                        }
                    )
                }
                "candidate" -> {
                    CandidateScreen(
                        onBack = {
                            currentScreen = "main"
                        },
                        onPlaceSearch = { endLat, endLng ->

                            val coords = MainActivityHolder.originCoords
                            Log.d("TRANSIT", "originCoords 개수: ${coords.size}")  // ← 추가
                            Log.d("TRANSIT", "endLat: $endLat, endLng: $endLng")



                            // ✅ 결과를 인덱스 순서대로 저장하기 위해 배열 사용
                            val results = arrayOfNulls<TransitResult?>(coords.size)
                            var completedCount = 0

                            coords.forEachIndexed { index, (startLat, startLng) ->
                                searchTransit(
                                    startLat = startLat,
                                    startLng = startLng,
                                    endLat = endLat,
                                    endLng = endLng,
                                    onSuccess = { result ->
                                        runOnUiThread {
                                            results[index] = result
                                            completedCount++
                                            if (completedCount == coords.size) {
                                                transitResults = results.toList()
                                                currentScreen = "result"
                                            }
                                        }
                                    },
                                    onFailure = { _ ->
                                        runOnUiThread {
                                            results[index] = null
                                            completedCount++
                                            if (completedCount == coords.size) {
                                                transitResults = results.toList()
                                                currentScreen = "result"
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    )
                }

                "result" -> {
                    ResultScreen(
                        transitResults = transitResults,

                        onBack = {
                            currentScreen = "candidate"
                        },

                        onCategoryClick = {
                            currentScreen = it
                        }
                    )
                }
                "detail" -> {

                    PlaceDetailScreen(

                        onBack = {
                            currentScreen = "restaurant"
                        },

                        onFavoriteClick = {

                            selectedPlace?.let {

                                FavoriteManager.add(
                                    FavoritePlace(
                                        name = it.name,
                                        category = it.category,
                                        address = "시흥시 정왕동",
                                        rating = it.rating,
                                        imageRes = it.imageRes
                                    )
                                )
                            }

                            currentScreen = "favorite"
                        }
                    )
                }
            }
        }
    }
}