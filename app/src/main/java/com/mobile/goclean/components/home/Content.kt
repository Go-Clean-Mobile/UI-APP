package com.mobile.goclean.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mobile.goclean.R
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.SubBackgroundColor
import com.mobile.goclean.theme.ooredo

@Composable
fun content(modifier: Modifier = Modifier) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(BackgroundGradient)
                .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            ConstraintLayout(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                val (text) = createRefs()
                Text(
                    text = "Welcome,  Regi!",
                    fontSize = 30.sp,
                    fontFamily = ooredo,
                    modifier =
                        Modifier.constrainAs(text) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                        },
                )
            }
            // Lokasi
            ConstraintLayout(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SubBackgroundColor.copy(alpha = 0.3f))
                        .padding(16.dp),
            ) {
                val (icon, text) = createRefs()
                Icon(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "Menu",
                    tint = OnPrimaryColor,
                    modifier =
                        Modifier
                            .size(20.dp)
                            .constrainAs(icon) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                )

                Text(
                    text = "Kelurahan Mojoroto, Kota Kediri, Indonesia",
                    fontWeight = FontWeight.ExtraBold,
                    modifier =
                        Modifier
                            .constrainAs(text) {
                                start.linkTo(icon.end, margin = 8.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                )
            }
            reportSummary()
        }
    }
}
