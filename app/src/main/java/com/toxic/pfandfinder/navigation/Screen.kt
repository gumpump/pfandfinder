package com.toxic.pfandfinder.navigation

sealed class Screen (val route: String)
{
	object CameraPermission: Screen("camera_permission_screen")
	object CameraScreen: Screen("camera_screen")
	object NoBottleScreen: Screen("no_bottle_screen")
	object ResultScreen: Screen("result_screen")
	object AddScreen: Screen("add_screen")
	object AddFormScreen: Screen("add_form_screen")
}