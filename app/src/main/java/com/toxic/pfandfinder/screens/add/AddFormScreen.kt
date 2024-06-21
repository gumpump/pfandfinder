package com.toxic.pfandfinder.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.toxic.pfandfinder.MainViewModel
import com.toxic.pfandfinder.ui.theme.PfandfinderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormScreen (navController: NavController,
                   mainViewModel: MainViewModel)
{
	val uiState by mainViewModel.mainUIState.collectAsState ()

	var bottleLabel by remember { mutableStateOf ("") }
	var bottleType by remember { mutableStateOf ("") }
	var bottleBarcode by remember { mutableStateOf (uiState.bottleBarcode) }

	PfandfinderTheme {
		Surface {
			Scaffold (  topBar = { TopAppBar (  colors = TopAppBarDefaults.topAppBarColors (  containerColor = MaterialTheme.colorScheme.primaryContainer,
				titleContentColor = MaterialTheme.colorScheme.primary),
				title = { Text ("Pfandgut hinzufügen") })
			},
				bottomBar = {   BottomAppBar ( containerColor = MaterialTheme.colorScheme.primaryContainer,
					contentColor = MaterialTheme.colorScheme.primary)
				{
				}})
			{
				Column (modifier = Modifier
					.padding(it)
					.fillMaxWidth(1.0f))
				{
					OutlinedTextField ( label = { Text ("Marke") },
										value = bottleLabel, onValueChange = { bottleLabel = it },
										modifier = Modifier.fillMaxWidth (1.0f))

					OutlinedTextField ( label = { Text ("Sorte") },
										value = bottleType, onValueChange = { bottleType = it },
										modifier = Modifier.fillMaxWidth (1.0f))

					OutlinedTextField ( label = { Text ("Barcode") },
										value = bottleBarcode, onValueChange = { bottleBarcode = it },
										keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Number),
										modifier = Modifier.fillMaxWidth (1.0f))

					Row (modifier = Modifier.padding (5.dp, 5.dp, 0.dp, 0.dp))
					{
						Button (onClick = {}, modifier = Modifier.padding (0.dp, 0.dp, 5.dp, 0.dp))
						{
							Text ("Add")
						}

						OutlinedButton (onClick = { navController.popBackStack () }, modifier = Modifier.padding (5.dp, 0.dp, 0.dp, 0.dp))
						{
							Text ("Cancel")
						}
					}
				}
			}
		}
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddFormScreenPreview ()
{
	AddFormScreen (NavController (LocalContext.current), viewModel ())
}