package com.pdmcourse.spotlyfe.ui.screens.AddPlaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesViewModel

@Composable
fun AddPlacesScreen(
  navController: NavHostController,
  viewModel: AddPlacesViewModel = viewModel(factory = AddPlacesViewModel .Factory)
) {
  var nameText by remember { mutableStateOf("") }
  var markerPosition by remember { mutableStateOf(LatLng(0.0, 0.0)) }
  var remarkText by remember { mutableStateOf("") }

  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(markerPosition, 10f)
  }

  Scaffold(
    topBar = {
      CustomTopBar(
        title = "Añadir lugar",
        onBackPressed = { navController.popBackStack() }
      )
    },
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      TextField(
        value = nameText,
        onValueChange = { nameText = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Escribe aquí...") },
        maxLines = 5
      )
      Spacer(modifier = Modifier.height(16.dp))
      TextField(
        value = remarkText,
        onValueChange = { remarkText = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Escribe una descripción...") },
        maxLines = 5
      )
      Spacer(modifier = Modifier.height(16.dp))
      GoogleMap(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        cameraPositionState = cameraPositionState,
        onMapClick = { markerPosition = it }
      ) {
        Marker(
          state = MarkerState(position = markerPosition),
          title = nameText.ifBlank { "Lugar sin nombre" }
        )
      }
      Spacer(modifier = Modifier.height(16.dp))
      Button(
        onClick = {
          viewModel.addPlace(nameText, remarkText, markerPosition)
          navController.popBackStack()
        },
        modifier = Modifier.align(Alignment.End)
      ) {
        Text("Subir")
      }
    }
  }
}