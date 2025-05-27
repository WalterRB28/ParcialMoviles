package com.pdmcourse.spotlyfe.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomFloatingButton(
  text: String = "Add Place",
  onClick: () -> Unit = {},
  icon: ImageVector = Icons.Filled.Add,
) {
  ExtendedFloatingActionButton(
    text = { Text(text) },
    icon = { Icon(imageVector = icon, contentDescription = text) },
    onClick = { onClick() },
  )
}