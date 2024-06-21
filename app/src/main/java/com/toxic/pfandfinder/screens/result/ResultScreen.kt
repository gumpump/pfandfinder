package com.toxic.pfandfinder.screens.result

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.toxic.pfandfinder.navigation.Screen
import com.toxic.pfandfinder.ui.theme.PfandfinderTheme

@Composable
fun ResultScreen (navController: NavController,
                  resultViewModel: ResultViewModel = viewModel (),
                  barcode: String)
{
	val uiState by resultViewModel.resultUIState.collectAsState ()
	val context = LocalContext.current.applicationContext as Application

	PfandfinderTheme {
		Surface {
			LaunchedEffect (key1 = true, block =    {
														resultViewModel.open (context)
														resultViewModel.loadData (barcode)
														Log.i ("selfmade", "Data has been loaded")
													})

			if (!uiState.found)
			{
				AlertDialog (   onDismissRequest = {},
								confirmButton = {   Button (onClick = { navController.navigate (route = Screen.CameraPermission.route) }, content = { Text ("Erneut scannen") })
													Button (onClick = { navController.navigate (route = Screen.AddScreen.route) }, content = { Text ("Flasche hinzufügen") })},
								title = { Text ("Flasche nicht erkannt") },
								text = { Text ("Diese Flasche wurde nicht erkannt.\nWollen sie es erneut versuchen?") })
			}

			else
			{
				// Main column containing the whole page
				Column(modifier = Modifier.fillMaxSize())
				{
					// Upper column containing the bottle name and the nearest stores
					Column(
						verticalArrangement = Arrangement.Center,
						modifier = Modifier
							.fillMaxWidth(1.0f)
							.fillMaxHeight((1.0f / 3))
					)
					{
						// Bottle name
						Text(
							textAlign = TextAlign.Center,
							text = uiState.bottleName,
							fontSize = 30.sp,
							modifier = Modifier.fillMaxWidth(1.0f)
						)
					}

					// Divider between the upper column and the lower grid
					Divider(
						thickness = 3.dp,
						color = Color.Black
					)

					// Grid containing the filter icons.
					LazyVerticalGrid(columns = GridCells.Adaptive(128.dp))
					{
						// List of filter icons
						items(uiState.stores) { store ->
							// Entry in the list of filter icons
							Icon(
								painter = painterResource (id = store.imagecode!!.toInt ()),
								contentDescription = null,
								modifier = Modifier
									.fillMaxWidth(1.0f)
									.height(100.dp)
									.padding(
										horizontal = 5.dp,
										vertical = 5.dp
									),
								tint = Color.Unspecified
							)
						}
					}
				}
			}
		}
	}
}

/* --- @Preview --- */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview ()
{
}

/* --- @Preview --- */