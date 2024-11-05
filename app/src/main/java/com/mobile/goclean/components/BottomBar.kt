package com.mobile.goclean.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.R
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.PrimaryColor
import com.mobile.goclean.theme.SubBackgroundColor

@Composable
fun bottomBar(
    navController: NavController,
    currentScreen: String,
) {
    BottomAppBar(
        containerColor = OnPrimaryColor,
        modifier = Modifier.clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp)),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            bottomBarIconButton(
                iconResId = R.drawable.home,
                contentDescription = "Menu",
                isActive = currentScreen == "home_screen",
                onClick = { navController.navigate("home_screen") },
            )
            Spacer(modifier = Modifier.weight(1f))

            bottomBarScanButton(onClick = { navController.navigate("add_screen") })

            Spacer(modifier = Modifier.weight(1f))
            bottomBarIconButton(
                iconResId = R.drawable.history,
                contentDescription = "History",
                isActive = currentScreen == "history_screen",
                onClick = { navController.navigate("history_screen") },
            )
        }
    }
}

@Composable
fun bottomBarIconButton(
    iconResId: Int,
    contentDescription: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            tint = if (isActive) PrimaryColor else SubBackgroundColor,
            modifier = Modifier.size(30.dp),
        )
    }
}

@Composable
fun bottomBarScanButton(onClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .size(60.dp)
                .background(SubBackgroundColor, CircleShape)
                .padding(10.dp),
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.scan),
                contentDescription = "Scan",
                tint = Color.White,
                modifier = Modifier.size(40.dp),
            )
        }
    }
}

@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    bottomBar(navController = navController, currentScreen = "")
}
