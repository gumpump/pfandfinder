package com.toxic.pfandfinder.screens.camera

import android.app.Application
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.toxic.pfandfinder.MainViewModel
import com.toxic.pfandfinder.navigation.Screen
import com.toxic.pfandfinder.ui.theme.PfandfinderTheme

@OptIn(ExperimentalGetImage::class) @Composable
fun CameraScreen (navController: NavController,
                  bottleCheckViewModel: BottleCheckViewModel = viewModel (),
				  mainViewModel: MainViewModel
)
{
	val context = LocalContext.current
	val app = LocalContext.current.applicationContext as Application
	val lifecycleOwner = LocalLifecycleOwner.current
	val cameraController = remember { LifecycleCameraController (context) }
	val options = remember {BarcodeScannerOptions   .Builder ()
													.setBarcodeFormats (Barcode.FORMAT_EAN_8,
																		Barcode.FORMAT_EAN_13)
													.build ()
	}
	val barcodeScanner = remember { BarcodeScanning.getClient (options) }

	PfandfinderTheme {
		Surface {
			Box (modifier = Modifier.fillMaxSize ())
			{
				AndroidView (   modifier = Modifier.fillMaxSize (),
								factory = { context ->
											PreviewView (context).apply {
												layoutParams = LinearLayout.LayoutParams (  ViewGroup.LayoutParams.MATCH_PARENT,
																							ViewGroup.LayoutParams.MATCH_PARENT)
												scaleType = PreviewView.ScaleType.FILL_START
											}.also { previewView ->
												previewView.controller = cameraController
												cameraController.bindToLifecycle (lifecycleOwner)
												cameraController.setEnabledUseCases (CameraController.IMAGE_ANALYSIS)
												cameraController.setImageAnalysisAnalyzer ( ContextCompat.getMainExecutor (context),
																							MlKitAnalyzer ( listOf (barcodeScanner),
																											CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED,
																											ContextCompat.getMainExecutor (context))
																							{ result: MlKitAnalyzer.Result? ->
																								val code = result?.getValue(barcodeScanner)?.firstOrNull();
																								if (code != null)
																								{
																									Log.i ("selfmade", "Barcode found: ${code.rawValue.toString ()}")
																									mainViewModel.setBottleBarcode (code.rawValue.toString ())
																									bottleCheckViewModel.open (app)

																									if (bottleCheckViewModel.bottleExist (code.rawValue.toString ()))
																									{
																										navController.navigate (route = Screen.ResultScreen.route    + "/" + code.rawValue.toString ())
																									}

																									else
																									{
																										navController.navigate (route = Screen.NoBottleScreen.route)
																									}
																								}
																							});
											}
										})

				Column (verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier .fillMaxWidth (0.9f)
											.align (Alignment.Center))
				{
					Column (modifier = Modifier .fillMaxWidth (1.0f)
												.height (250.dp)
												.border (BorderStroke (5.dp, Color.White)))
					{/*This column represents the white rectangle*/}
					Text(   text = "Barcode hier rein",
							textAlign = TextAlign.Center,
							fontSize = 15.sp,
							color = Color.White,
							modifier = Modifier.width (180.dp))
				}
			}
		}
	}
}

@Preview (showSystemUi = true, showBackground = true)
@Composable
fun CameraScreenPreview ()
{
	PfandfinderTheme {
		Surface {
			Box (modifier = Modifier.fillMaxSize ())
			{
				Column (verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier .fillMaxWidth (0.9f)
										.align (Alignment.Center))
				{
					Column (modifier = Modifier .fillMaxWidth (1.0f)
						.height (250.dp)
						.border (BorderStroke (5.dp, Color.Black)))
					{}
					Text(   text = "Barcode hier rein",
							textAlign = TextAlign.Center,
							fontSize = 25.sp,
							color = Color.Black,
							modifier = Modifier.fillMaxWidth ());
				}
			}
		}
	}
}