package com.toxic.pfandfinder.screens.camera

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.toxic.pfandfinder.R
import com.toxic.pfandfinder.database.OfflineItemsRepository
import com.toxic.pfandfinder.database.PfandfinderDatabase

class BottleCheckViewModel () : ViewModel ()
{
	private lateinit var repository: OfflineItemsRepository

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
		}

		catch (e: Exception)
		{
			// Shit hits the fan
			Log.e ("selfmade", e.message.toString ())
		}
	}

	fun bottleExist (barcode: String): Boolean
	{
		if (repository.bottleExist (barcode) > 0)
		{
			return true
		}

		else
		{
			return false
		}
	}
}