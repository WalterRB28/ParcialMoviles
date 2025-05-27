package com.pdmcourse.spotlyfe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse.spotlyfe.data.model.Place

@Entity(tableName = "places")
data class PlaceEntity (
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val name: String,
  val remark: String,
  val latitude: Double,
  val longitude: Double,
)

fun PlaceEntity.toDomain(): Place {
  return Place(
    id = id,
    name = name,
    remark = remark,
    latitude = latitude,
    longitude = longitude
  )
}