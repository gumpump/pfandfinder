package com.toxic.pfandfinder

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.toxic.pfandfinder.database.OfflineItemsRepository
import com.toxic.pfandfinder.database.PfandfinderDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel()
{
	private lateinit var repository: OfflineItemsRepository

	private val _mainUIState = MutableStateFlow (MainUIState ())
	val mainUIState: StateFlow<MainUIState> = _mainUIState.asStateFlow ()

	fun openDatabase (application: Application)
	{
		try
		{
			Log.i ("selfmade", "Get instance of database")
			val db = PfandfinderDatabase.getInstance (application)

			Log.i ("selfmade", "Get access to queries")
			val dao = db.databaseQueries ()

			Log.i ("selfmade", "Get access to layer 1")
			repository = OfflineItemsRepository(dao)
		}

		catch (e: Exception)
		{
			// Shit hits the fan
			Log.e("selfmade", e.message.toString())
		}
	}

	fun setBottleLabel (label: String)
	{
		this._mainUIState.value = MainUIState ( bottleLabel = label,
												bottleType = this._mainUIState.value.bottleType,
												bottleBarcode = this._mainUIState.value.bottleBarcode,
												stores = this._mainUIState.value.stores)
	}

	fun setBottleType (type: String)
	{
		this._mainUIState.value = MainUIState ( bottleLabel = this._mainUIState.value.bottleLabel,
												bottleType = type,
												bottleBarcode = this._mainUIState.value.bottleBarcode,
												stores = this._mainUIState.value.stores)
	}

	fun setBottleBarcode (barcode: String)
	{
		this._mainUIState.value = MainUIState ( bottleLabel = this._mainUIState.value.bottleLabel,
												bottleType = this._mainUIState.value.bottleType,
												bottleBarcode = barcode,
												stores = this._mainUIState.value.stores)
	}
}