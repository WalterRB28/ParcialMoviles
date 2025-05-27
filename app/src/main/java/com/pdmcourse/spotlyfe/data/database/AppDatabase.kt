package com.pdmcourse.spotlyfe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdmcourse.spotlyfe.data.database.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity

@Database(
  entities = [PlaceEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
  abstract fun placeDao(): PlaceDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context = context.applicationContext,
          klass = AppDatabase::class.java,
          name = "spotlyfe_database"
        )
          .fallbackToDestructiveMigration(false)
          .build()
          .also {
            INSTANCE = it
          }
        instance
      }
    }
  }
}