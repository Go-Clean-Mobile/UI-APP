package com.mobile.goclean.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.goclean.R
import com.mobile.goclean.theme.Blue100
import com.mobile.goclean.theme.Blue400
import com.mobile.goclean.theme.Green100
import com.mobile.goclean.theme.Green400
import com.mobile.goclean.theme.Yellow100
import com.mobile.goclean.theme.Yellow400
import com.mobile.goclean.theme.ooredo

@Composable
fun historyItem(
    date: String,
    points: Int,
    type: Int,
    modifier: Modifier = Modifier,
) {
    val (iconResId, iconTint, backgroundColor) =
        when (type) {
            1 -> Triple(R.drawable.leaf, Green400, Green100)
            2 -> Triple(R.drawable.recycle, Blue400, Blue100)
            else -> Triple(R.drawable.trash, Color.Gray, Color.LightGray)
        }

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .background(backgroundColor, RoundedCornerShape(50.dp))
                    .padding(10.dp),
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(30.dp),
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = date,
                style =
                    TextStyle(
                        fontFamily = ooredo,
                        color = Color.Gray,
                        fontSize = 16.sp,
                    ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier =
                    Modifier
                        .background(Yellow100, RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
            ) {
                Text(
                    text = "+ $points Point",
                    style =
                        TextStyle(
                            fontFamily = ooredo,
                            color = Yellow400,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
            }
        }
    }
}
