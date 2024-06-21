package com.toxic.pfandfinder.screens.result

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.toxic.pfandfinder.database.DatabaseTable__bottle
import com.toxic.pfandfinder.database.OfflineItemsRepository
import com.toxic.pfandfinder.database.PfandfinderDatabase
import com.toxic.pfandfinder.database.Store
import com.toxic.pfandfinder.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Layer 3
class ResultViewModel () : ViewModel ()
{
	// Access to the queries
	private lateinit var repository: OfflineItemsRepository

	// Variables accessed by the UI
	private val _resultUIState = MutableStateFlow (ResultUIState());
	val resultUIState: StateFlow<ResultUIState> = _resultUIState.asStateFlow ()

	// Link between database imagecodes and real imagecodes
	private var imagecodes: MutableMap<Int, Int> = mutableMapOf<Int, Int> ()

	// Temporary list of stores used for exchange imagecodes
	private var newStores: MutableList<Store> = mutableListOf ()

	// Initialize the database
	fun open (application: Application)
	{
		try
		{
			Log.i ("selfmade", "Get instance of database")
			val db = PfandfinderDatabase.getInstance (application)

			Log.i ("selfmade", "Get access to queries")
			val dao = db.databaseQueries ()

			Log.i ("selfmade", "Get access to layer 1")
			repository = OfflineItemsRepository(dao)

			Log.i ("selfmade", "Get imagecodes out of file")
			// Please don't look at this
			imagecodes[1] = R.drawable.logo_aldi_sued
			imagecodes[2] = R.drawable.logo_aldi_nord
			imagecodes[3] = R.drawable.logo_lidl
			imagecodes[4] = R.drawable.logo_penny
			imagecodes[5] = R.drawable.logo_edeka
			imagecodes[6] = R.drawable.logo_rewe
			imagecodes[7] = R.drawable.logo_kaufland
			imagecodes[8] = R.drawable.logo_netto
			imagecodes[9] = R.drawable.logo_globus
		}

		catch (e: Exception)
		{
			// Shit hits the fan
			Log.e ("selfmade", e.message.toString ())
		}
	}

	suspend fun insert (newBottle: DatabaseTable__bottle)
	{
		repository.insert (newBottle)
	}

	suspend fun update (newBottle: DatabaseTable__bottle)
	{
		repository.update (newBottle)
	}

	suspend fun delete (bottle: DatabaseTable__bottle)
	{
		repository.delete (bottle)
	}

	private fun getBottle (barcode: String): DatabaseTable__bottle?
	{
		return repository.getBottle(barcode)
	}

	private fun getStores (barcode: String): List<Store?>
	{
		return repository.getStores (barcode)
	}

	// Load data from the database
	fun loadData (barcode: String)
	{
		try
		{
			// Get the bottle by its barcode
			val bottle: DatabaseTable__bottle? = this.getBottle(barcode)

			if (bottle == null)
			{
				Log.e ("selfmade", "Couldn't find bottle related to that barcode")
				this._resultUIState.value = ResultUIState (found = false)

				return
			}

			Log.i ("selfmade", "Bottle found: ${bottle.fullname}")

			// Get a list of stores by the barcode
			val stores: List<Store?> = this.getStores(barcode)

			if (stores.isEmpty())
			{
				Log.e ("selfmade", "Couldn't find stores related to that bottle")
				this._resultUIState.value = ResultUIState (found = false)

				return;
			}

			Log.i ("selfmade", "${stores.filterNotNull().count()} stores found")

			// Exchange the image codes
			stores.forEach {
				if (it != null)
				{
					newStores.add(Store(it.name, imagecodes[it.imagecode]))
					Log.i ("selfmade", "${it.imagecode} changed to ${imagecodes[it.imagecode]}")
				}
			}

			this._resultUIState.value = ResultUIState(barcode, bottle.fullname, newStores, true)
		}

		catch (e: Exception)
		{
			Log.e ("selfmade", e.message.toString ())
		}
	}
}