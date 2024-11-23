package com.mobile.goclean.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.goclean.viewmodel.UserViewModel

@Composable
fun welcomeSection(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = UserViewModel(),
) {
    val userFirstName by userViewModel.userFirstName // Pantau perubahan state
    val errorMessage by userViewModel.errorMessage

    LaunchedEffect(Unit) {
        userViewModel.fetchUserProfile() // Fetch data user saat composable diload
    }

    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = if (userFirstName != null) "Welcome, $userFirstName!" else "Welcome!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp),
        )

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}

@Preview
@Composable
private fun prev() {
    welcomeSection()
}
