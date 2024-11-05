package com.mobile.goclean.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
// import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.R
import com.mobile.goclean.theme.BackgroundColor
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.SubBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(navController: NavController) {
    TopAppBar(
        title = {
            Box(
                modifier =
                    Modifier
                        .background(OnPrimaryColor, CircleShape)
                        .size(40.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "Menu",
                    tint = SubBackgroundColor,
                    modifier = Modifier.size(30.dp),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { navController.navigate("profile_screen") },
                modifier =
                    Modifier
                        .padding(15.dp)
                        .size(40.dp),
            ) {
                val image: Painter = painterResource(id = R.drawable.profil)
                Image(
                    painter = image,
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .background(Color.Gray, CircleShape)
                            .clip(CircleShape)
                            .size(40.dp),
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundColor,
                titleContentColor = Color.White,
            ),
    )
}

@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    topBar(navController = navController)
}
