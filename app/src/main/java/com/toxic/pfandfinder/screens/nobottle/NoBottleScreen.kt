package com.toxic.pfandfinder.screens.nobottle

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.toxic.pfandfinder.navigation.Screen

@Composable
fun NoBottleScreen (navController: NavController)
{
	AlertDialog (   onDismissRequest = {},
					confirmButton = {   Button (onClick = { navController.navigate (route = Screen.CameraPermission.route) }, content = { Text ("Erneut scannen") })
										Button (onClick = { navController.navigate (route = Screen.AddScreen.route) }, content = { Text ("Flasche hinzufügen") }) },
					title = { Text ("Flasche nicht erkannt") },
					text = { Text ("Diese Flasche wurde nicht erkannt.\nWollen sie es erneut versuchen?") })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoBottleScreenPreview ()
{
	NoBottleScreen(navController = rememberNavController ())
}