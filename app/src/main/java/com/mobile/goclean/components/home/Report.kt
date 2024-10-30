package com.mobile.goclean.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.goclean.R
import com.mobile.goclean.theme.Blue400
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.PrimaryColor
import com.mobile.goclean.theme.Red400
import com.mobile.goclean.theme.SubBackgroundColor
import com.mobile.goclean.theme.TitleTextColor
import com.mobile.goclean.theme.Yellow400

@Composable
fun reportSection(modifier: Modifier = Modifier) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            InfoBox(
                title = "Total",
                value = "120",
                iconResId = R.drawable.trash,
                modifier = Modifier.weight(1f),
                bg = Red400,
            )
            InfoBox(
                title = "Organik",
                value = "80",
                iconResId = R.drawable.leaf,
                modifier = Modifier.weight(1f),
                bg = PrimaryColor,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            InfoBox(
                title = "Anorganik",
                value = "40",
                iconResId = R.drawable.recycle,
                modifier = Modifier.weight(1f),
                bg = Blue400,
            )
            InfoBox(
                title = "Point",
                value = "788",
                iconResId = R.drawable.copper_coin,
                modifier = Modifier.weight(1f),
                bg = Yellow400,
            )
        }
    }
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
