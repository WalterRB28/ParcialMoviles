package com.pdmcourse.spotlyfe.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
  @Query("SELECT * FROM places")
  fun getPlaces(): Flow<List<PlaceEntity>>

  @Query("SELECT * FROM places WHERE id = :placeId")
  fun getPlaceById(placeId: Int): Flow<PlaceEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addPlace(place: PlaceEntity)

  @Delete
  suspend fun removePlace(place: PlaceEntity)
}