package com.toxic.pfandfinder.screens.camera

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.toxic.pfandfinder.navigation.Screen

@Composable
fun CameraPermission (navController: NavController)
{
	val perm = remember { mutableStateOf(false) }
	val launcher = rememberLauncherForActivityResult (contract = ActivityResultContracts.RequestPermission ()) { isGranted: Boolean ->
		perm.value = isGranted;
	}

	if (perm.value)
	{
		navController.navigate(route = Screen.CameraScreen.route);
	}

	else
	{
		LaunchedEffect (perm.value)
		{
			launcher.launch (Manifest.permission.CAMERA);
		}
	}
}