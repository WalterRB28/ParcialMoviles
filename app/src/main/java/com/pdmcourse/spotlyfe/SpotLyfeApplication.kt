package com.pdmcourse.spotlyfe

import android.app.Application
import com.pdmcourse.spotlyfe.data.AppProvider

class SpotLyfeApplication: Application() {
  val appProvider by lazy {
    AppProvider(this)
  }
}