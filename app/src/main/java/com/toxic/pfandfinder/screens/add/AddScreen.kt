package com.toxic.pfandfinder.screens.add

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.toxic.pfandfinder.database.BottleInfo
import com.toxic.pfandfinder.navigation.Screen
import com.toxic.pfandfinder.ui.theme.PfandfinderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen (navController: NavController,
			   addViewModel: AddViewModel = viewModel ())
{
	val uiState by addViewModel.addUIState.collectAsState ()
	val context = LocalContext.current.applicationContext as Application

	PfandfinderTheme {
		Surface {
			LaunchedEffect (key1 = true, block =    {
														addViewModel.open (context)
														addViewModel.loadData ()
														Log.i ("selfmade", "Data has been loaded")
													})
			Scaffold (  topBar = { TopAppBar (  colors = topAppBarColors (  containerColor = MaterialTheme.colorScheme.primaryContainer,
				titleContentColor = MaterialTheme.colorScheme.primary),
				title = { Text ("Hinzugefügtes Pfandgut") })},
				bottomBar = {   BottomAppBar ( containerColor = MaterialTheme.colorScheme.primaryContainer,
					contentColor = MaterialTheme.colorScheme.primary)
				{
				}},
				floatingActionButton = { FloatingActionButton (onClick = { navController.navigate (route = Screen.AddFormScreen.route) })
				{
					Icon (  imageVector = Icons.Default.Add,
						contentDescription = null)
				}})
			{
				LazyColumn (modifier = Modifier.padding (it))
				{
					items (items = uiState.bottles, itemContent =
													{bottle ->
														ListItem (  headlineContent = { Text (text = bottle.name!!.toString ()) },
																	supportingContent = { Text (text = bottle.barcode!!.toString ()) },
																	trailingContent = { Text ("Anzahl Läden: ${bottle.storecount}") },
																	leadingContent = {})
														Divider ()
													})
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DebugAddScreen ()
{
	PfandfinderTheme {
		Surface {
			Scaffold (  topBar = { TopAppBar (  colors = topAppBarColors (  containerColor = MaterialTheme.colorScheme.primaryContainer,
				titleContentColor = MaterialTheme.colorScheme.primary),
				title = { Text ("Hinzugefügtes Pfandgut") })},
				bottomBar = {   BottomAppBar ( containerColor = MaterialTheme.colorScheme.primaryContainer,
					contentColor = MaterialTheme.colorScheme.primary)
				{
				}},
				floatingActionButton = {    FloatingActionButton(onClick = {})
				{
					Icon (  imageVector = Icons.Default.Add,
						contentDescription = null)
				}})
			{
				LazyColumn (modifier = Modifier.padding (it))
				{
					items (items = listOf<BottleInfo> ( BottleInfo (name = "Flasche 1", barcode = "12345678", 1),
														BottleInfo (name = "Flasche 2", barcode = "12345678", 1)), itemContent =
					{bottle ->
						ListItem (  headlineContent = { Text (text = bottle.name!!.toString ()) },
							supportingContent = { Text (text = bottle.barcode!!.toString ()) },
							trailingContent = { Text ("Anzahl Läden: ${bottle.storecount}") },
							leadingContent = {})
						Divider ()
					})
				}
			}
		}
	}
}