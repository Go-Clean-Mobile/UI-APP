package com.mobile.goclean.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import com.mobile.goclean.viewmodel.SummaryViewModel

@Composable
fun reportSection(
    modifier: Modifier = Modifier,
    viewModel: SummaryViewModel = SummaryViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchReportSummary()
    }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            infoBox(
                title = "Total",
                value = (state.organic + state.inorganic).toString(),
                iconResId = R.drawable.trash,
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                bg = Red400,
            )
            infoBox(
                title = "Organik",
                value = state.organic.toString(),
                iconResId = R.drawable.leaf,
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                bg = PrimaryColor,
            )
        }

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            infoBox(
                title = "Anorganik",
                value = state.inorganic.toString(),
                iconResId = R.drawable.recycle,
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                bg = Blue400,
            )
            infoBox(
                title = "Point",
                value = state.points.toString(),
                iconResId = R.drawable.copper_coin,
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                bg = Yellow400,
            )
        }
    }
}

@Composable
fun infoBox(
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
                        .size(40.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    tint = bg,
                    modifier = Modifier.size(38.dp),
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TitleTextColor,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier =
                        Modifier
                            .background(bg, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp),
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

@Preview
@Composable
private fun previewReportSection() {
    reportSection()
}
