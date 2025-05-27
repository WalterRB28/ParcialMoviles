package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse.spotlyfe.SpotLyfeApplication
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.data.repository.Place.PlaceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SavedPlacesViewModel(
  private val placeRepository: PlaceRepository
): ViewModel() {

  val places: StateFlow<List<Place>> = placeRepository.getPlaces()
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList()
    )

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val aplication = this[APPLICATION_KEY] as SpotLyfeApplication
        SavedPlacesViewModel(
          aplication.appProvider.providePlaceRepository()
        )
      }
    }
  }
}