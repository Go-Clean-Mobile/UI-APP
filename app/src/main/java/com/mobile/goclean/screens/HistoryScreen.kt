package com.mobile.goclean.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.R
import com.mobile.goclean.components.bottomBar
import com.mobile.goclean.components.topBar
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.Blue400
import com.mobile.goclean.theme.Green100
import com.mobile.goclean.theme.Green50
import com.mobile.goclean.theme.Green600
import com.mobile.goclean.theme.Green700
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.SubBackgroundColor
import com.mobile.goclean.theme.TitleTextColor
import com.mobile.goclean.theme.Yellow100
import com.mobile.goclean.theme.Yellow400
import com.mobile.goclean.theme.Yellow700
import com.mobile.goclean.theme.ooredo

@Composable
fun HistoryScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route ?: "login_screen"
    Scaffold(
        topBar = { topBar(navController = navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().background(BackgroundGradient).padding(paddingValues),
//                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
//                Text(
//                    "[History]",
//                    style = MaterialTheme.typography.titleLarge,
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.kucing),
//                    contentDescription = "Profile Image",
//                    modifier = Modifier.fillMaxSize(),
//                )

                Column (
                    modifier = Modifier.padding(20.dp)
                ){
                    Column {
                        Row(
                            modifier = Modifier.background(Color.White, RoundedCornerShape(12.dp)).padding(15.dp).fillMaxWidth(),
                        ){
                            Box(
                                modifier = Modifier.background(Green100, RoundedCornerShape(50.dp)).padding(10.dp)
                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.history),
                                    contentDescription = "Menu",
                                    tint = Green700,
                                    modifier = Modifier.size(30.dp),
                                )
                            }

                            Column {
                                Text("17/08/2002",
                                    style = TextStyle(
                                        fontFamily = ooredo,
                                        color = SubBackgroundColor,
                                        fontSize = 18.sp
                                    ),
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                                Box(
                                    modifier = Modifier.padding(start = 10.dp).background(Yellow100, RoundedCornerShape(20.dp)).padding(8.dp, 2.dp)
                                ){
                                    Text("+ 88 Point",
                                        style = TextStyle(
                                            fontFamily = ooredo,
                                            color = Yellow400,
                                            fontSize = 18.sp
                                        ),
                                    )
                                }
                            }
                            InfoBox(
                                title = "Anorganik",
                                value = "40",
                                iconResId = R.drawable.recycle,
                                modifier = Modifier.weight(1f).offset(y=-10.dp),
                                bg = Blue400,
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier =
                    Modifier
                        .background(Green600),
            ) {
                bottomBar(navController = navController, currentScreen = currentScreen)
            }
        },
        contentColor = Color.White,
    )
}




@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    HistoryScreen(navController = navController)
}


@Composable
fun InfoBox(
    title: String,
    value: String,
    iconResId: Int,
    modifier: Modifier = Modifier,
    bg: Color,
) {
    Box(
        modifier =
        modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                Modifier
                    .background(SubBackgroundColor.copy(alpha = 0.1f), RoundedCornerShape(10.dp))
                    .width(40.dp),
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    tint = bg,
                    modifier = Modifier.size(40.dp),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TitleTextColor,
                )
                Spacer(Modifier.size(4.dp))
                Row(
                    modifier = Modifier.background(bg, RoundedCornerShape(4.dp)).padding(4.dp, 1.dp),
                ) {
                    Text(
                        text = value,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = OnPrimaryColor,
                    )
                }
            }
        }
    }
}

