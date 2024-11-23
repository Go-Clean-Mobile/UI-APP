package com.mobile.goclean.screens

import DetectionViewModel
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.mobile.goclean.R
import com.mobile.goclean.components.bottomBar
import com.mobile.goclean.components.topBar
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.Green600
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.PrimaryColor
import com.mobile.goclean.theme.ooredo
import kotlinx.coroutines.launch

@Composable
fun addScreen(
    navController: NavController,
    viewModel: DetectionViewModel = viewModel(),
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var jsonResponse by remember { mutableStateOf<String>("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var isSubmitting by remember { mutableStateOf(false) }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                selectedImageUri = it
            }
        }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = {
                Text(
                    text = "Hasil Deteksi",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = ooredo),
                )
            },
            text = {
                Text(
                    text = jsonResponse,
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray, fontFamily = ooredo),
                )
            },
            confirmButton = {
                Button(
                    onClick = { showSuccessDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Close", color = Color.White, fontFamily = ooredo)
                }
            },
        )
    }

    Scaffold(
        topBar = { topBar(navController = navController) },
        content = { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(BackgroundGradient)
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(OnPrimaryColor, RoundedCornerShape(16.dp))
                            .padding(bottom = 20.dp, top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = { launcher.launch("image/*") }, // Membuka launcher untuk memilih gambar
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                        shape = RoundedCornerShape(12.dp),
                        modifier =
                            Modifier
                                .fillMaxWidth(0.8f)
                                .padding(vertical = 16.dp),
                    ) {
                        Text(
                            text = "Pilih Gambar",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = ooredo),
                        )
                    }

                    selectedImageUri?.let { uri ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.9f)
                                    .background(Color.White, RoundedCornerShape(12.dp))
                                    .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = rememberImagePainter(data = uri),
                                contentDescription = "Selected Image",
                                modifier =
                                    Modifier
                                        .fillMaxWidth(0.9f)
                                        .aspectRatio(16f / 9f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop,
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Nama File: ${uri.lastPathSegment}",
                                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                scope.launch {
                                    isSubmitting = true
                                    jsonResponse = ""
                                    try {
                                        val part = viewModel.uploadImage(context, uri)
                                        val result = viewModel.getJsonResponse(part)

                                        if (result.contains("anorganik", ignoreCase = true)) {
                                            viewModel.insertHistoryToDatabase(points = 10, category = "2")
                                        } else if (result.contains("organik", ignoreCase = true)) {
                                            viewModel.insertHistoryToDatabase(points = 10, category = "1")
                                        }

                                        jsonResponse = result
                                        showSuccessDialog = true
                                    } catch (e: Exception) {
                                        jsonResponse = "Gagal mengunggah gambar: ${e.message}"
                                        showSuccessDialog = true
                                    } finally {
                                        isSubmitting = false
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Green600),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(0.8f),
                            enabled = !isSubmitting,
                        ) {
                            if (isSubmitting) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(20.dp),
                                )
                            } else {
                                Text(
                                    text = "Submit",
                                    style =
                                        TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = ooredo,
                                        ),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.send),
                                    contentDescription = "Submit",
                                )
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.background(Green600),
            ) {
                bottomBar(navController = navController, currentScreen = "add_screen")
            }
        },
    )
}

@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    addScreen(navController = navController)
}
