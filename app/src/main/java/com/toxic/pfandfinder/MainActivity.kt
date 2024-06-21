package com.toxic.pfandfinder

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toxic.pfandfinder.navigation.Navigation

class MainActivity : ComponentActivity ()
{
	/* --- Overridden --- */

	// It seems like it is a kind of constructor
	override fun onCreate (savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState);

		/* --- SETCONTENT --- */
		setContent {
			val activity = LocalContext.current as Activity;
			activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

			val context = LocalContext.current.applicationContext as Application

			// ViewModelStoreOwner removed
			// If shit hits the fan, restore the project from USB
			val mainViewModel: MainViewModel = viewModel ()
			mainViewModel.openDatabase (context)
			Navigation (mainViewModel);
		}
	}

	// It seems like it is a kind of destructor
	override fun onDestroy ()
	{
		super.onDestroy ();
	}

	/* --- Overridden --- */
}