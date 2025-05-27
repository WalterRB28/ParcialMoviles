package com.pdmcourse.spotlyfe.data.repository.Place

import com.pdmcourse.spotlyfe.data.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
  fun getPlaces(): Flow<List<Place>>
  suspend fun addPlace(place: Place)
  suspend fun removePlace(place: Place)
}