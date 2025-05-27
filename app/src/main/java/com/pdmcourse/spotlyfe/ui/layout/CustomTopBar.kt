package com.pdmcourse.spotlyfe.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
  title: String = "SpotLyfe",
  onBackPressed:  (() -> Unit)? = null,
) {
  TopAppBar(
    title = { Text(title) },
    navigationIcon = {
      if (onBackPressed != null) {
        IconButton(onClick = onBackPressed) {
          Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
      }
    }
  )
}