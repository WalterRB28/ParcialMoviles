package com.pdmcourse.spotlyfe.ui.screens.AddPlaces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.maps.model.LatLng
import com.pdmcourse.spotlyfe.SpotLyfeApplication
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.data.repository.Place.PlaceRepository
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddPlacesViewModel(
  private val placeRepository: PlaceRepository
): ViewModel() {

  val places: StateFlow<List<Place>> = placeRepository.getPlaces()
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList()
    )

  fun addPlace(name: String, remark: String, location: LatLng) {
    viewModelScope.launch {
      val place = Place(
        id = 0,
        name = name,
        remark = remark,
        latitude = location.latitude,
        longitude = location.longitude
      )
      placeRepository.addPlace(place)
    }
  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val aplication = this[APPLICATION_KEY] as SpotLyfeApplication
        AddPlacesViewModel(
          aplication.appProvider.providePlaceRepository()
        )
      }
    }
  }
}