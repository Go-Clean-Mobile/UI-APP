package com.mobile.goclean.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mobile.goclean.R
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.SubBackgroundColor

@Composable
fun welcomeSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = "Welcome, Regi!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp),
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SubBackgroundColor.copy(alpha = 0.3f))
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Menu",
                tint = OnPrimaryColor,
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Kelurahan Mojoroto, Kota Kediri, Indonesia",
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}
