package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import com.pdmcourse.spotlyfe.ui.navigation.AddPlaceScreenNavigation

@Composable
fun SavedPlacesScreen(
  navController: NavHostController,
  viewModel: SavedPlacesViewModel = viewModel(factory = SavedPlacesViewModel.Factory)
) {
  val places by viewModel.places.collectAsState()

  val cameraPositionState = rememberCameraPositionState()

  var uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
  }
  var properties by remember {
    mutableStateOf(MapProperties(mapType = MapType.HYBRID))
  }

  LaunchedEffect(places) {
    if (places.isNotEmpty()) {
      val boundsBuilder = LatLngBounds.builder()
      places.forEach { place ->
        boundsBuilder.include(LatLng(place.latitude, place.longitude))
      }
      val bounds = boundsBuilder.build()
      cameraPositionState.move(
        CameraUpdateFactory.newLatLngBounds(bounds, 100)
      )
    }
  }

  if (places.isEmpty()) {
    Scaffold(
      topBar = { CustomTopBar() },
      floatingActionButton = { CustomFloatingButton(onClick = {
        navController.navigate(AddPlaceScreenNavigation)
      })}
    ) { innerPadding ->
      Column(modifier = Modifier.padding(innerPadding)) {
        Text("Da click en añadir para crear una nueva ubicacion")
      }
    }
  } else {
    Scaffold(
      topBar = { CustomTopBar() },
      floatingActionButton = { CustomFloatingButton(onClick = {
        navController.navigate(AddPlaceScreenNavigation)
      })}
    ) { innerPadding ->
      Column(modifier = Modifier.padding(innerPadding)) {

        GoogleMap(
          modifier = Modifier.fillMaxSize(),
          cameraPositionState = cameraPositionState,
          properties = properties,
          uiSettings = uiSettings
        ) {
          places.forEach { place ->
            Marker(
              state = MarkerState(position = LatLng(place.latitude, place.longitude)),
              title = place.name,
              snippet = place.remark
            )
          }
        }
      }
    }
  }
}