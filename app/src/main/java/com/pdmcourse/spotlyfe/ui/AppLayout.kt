package com.pdmcourse.spotlyfe.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.pdmcourse.spotlyfe.ui.navigation.MainNavigation

@Composable
fun AppLayout() {
  val navController = rememberNavController()

  MainNavigation(navController = navController)
}