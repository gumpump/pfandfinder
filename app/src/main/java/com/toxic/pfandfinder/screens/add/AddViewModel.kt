package com.toxic.pfandfinder.screens.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.toxic.pfandfinder.database.BottleInfo
import com.toxic.pfandfinder.database.DatabaseTable__bottle
import com.toxic.pfandfinder.database.OfflineItemsRepository
import com.toxic.pfandfinder.database.PfandfinderDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddViewModel : ViewModel()
{
	private lateinit var repository: OfflineItemsRepository

	private val _addUIState = MutableStateFlow(AddUIState ())
	val addUIState: StateFlow<AddUIState> = _addUIState.asStateFlow ()

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
		}

		catch (e: Exception)
		{
			// Shit hits the fan
			Log.e("selfmade", e.message.toString())
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

	private fun getInfo (): List<BottleInfo?>
	{
		return repository.getInfo ()
	}

	fun loadData ()
	{
		try
		{
			this._addUIState.value = AddUIState (getInfo().filterNotNull ())
		}

		catch (e: Exception)
		{
			Log.e ("selfmade", e.message.toString ())
		}
	}
}