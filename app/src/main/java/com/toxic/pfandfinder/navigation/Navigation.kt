package com.toxic.pfandfinder.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.toxic.pfandfinder.MainViewModel
import com.toxic.pfandfinder.screens.add.AddFormScreen
import com.toxic.pfandfinder.screens.add.AddScreen
import com.toxic.pfandfinder.screens.camera.CameraPermission
import com.toxic.pfandfinder.screens.camera.CameraScreen
import com.toxic.pfandfinder.screens.nobottle.NoBottleScreen
import com.toxic.pfandfinder.screens.result.ResultScreen

@Composable
fun Navigation (mainViewModel: MainViewModel)
{
	val navController = rememberNavController ()

	NavHost (navController = navController, startDestination = Screen.CameraPermission.route)
	{
		composable (route = Screen.CameraPermission.route)
		{
			CameraPermission (navController = navController)
		}

		composable (route = Screen.CameraScreen.route)
		{
			CameraScreen (navController = navController, mainViewModel = mainViewModel)
		}

		composable (route = Screen.NoBottleScreen.route)
		{
			NoBottleScreen (navController = navController)
		}

		composable (route = Screen.ResultScreen.route + "/{barcode}",
					arguments = listOf (navArgument ("barcode")
										{
											type = NavType.StringType
											defaultValue = "empty"
										}))
		{ entry ->
			ResultScreen (navController = navController, barcode = entry.arguments?.getString("barcode").toString ())
		}

		composable (route = Screen.AddScreen.route)
		{
			AddScreen (navController = navController)
		}

		composable (route = Screen.AddFormScreen.route)
		{
			AddFormScreen (navController, mainViewModel)
		}
	}
}