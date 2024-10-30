package com.mobile.goclean.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
fun HistoryItems(
    tanggal: String,
    jumlahSampah: Int,
    type: Int,
    modifier: Modifier = Modifier,
) {
    val (iconResId, iconTint, backgroundColor) =
        when (type) {
            1 -> Triple(R.drawable.leaf, Green400, Green100)
            2 -> Triple(R.drawable.recycle, Blue400, Blue100)
            else -> Triple(R.drawable.leaf, Color.Gray, Color.LightGray)
        }

    Row(
        modifier =
            modifier
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(15.dp)
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .background(backgroundColor, RoundedCornerShape(50.dp)) // Background berdasarkan type
                    .padding(10.dp),
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "Icon Sampah",
                tint = iconTint,
                modifier = Modifier.size(30.dp),
            )
        }

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                tanggal,
                style =
                    TextStyle(
                        fontFamily = ooredo,
                        color = Color.Gray,
                        fontSize = 18.sp,
                    ),
            )
            Box(
                modifier =
                    Modifier
                        .padding(top = 4.dp)
                        .background(Yellow100, RoundedCornerShape(20.dp))
                        .padding(8.dp, 2.dp),
            ) {
                Text(
                    text = "+ $jumlahSampah Point",
                    style =
                        TextStyle(
                            fontFamily = ooredo,
                            color = Yellow400,
                            fontSize = 18.sp,
                        ),
                )
            }
        }
    }
}
