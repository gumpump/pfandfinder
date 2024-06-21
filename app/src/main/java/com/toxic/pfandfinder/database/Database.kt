package com.toxic.pfandfinder.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database ( entities = [(DatabaseTable__bottle::class),
						(DatabaseTable__store::class),
						(DatabaseTable__bottle_store::class)],
			version = 1,
			exportSchema = false)
abstract class PfandfinderDatabase: RoomDatabase ()
{
	abstract fun databaseQueries (): DatabaseQueries;

	companion object
	{
		@Volatile
		private var INSTANCE: PfandfinderDatabase? = null;

		fun getInstance (context: Context): PfandfinderDatabase
		{
			Log.i ("selfmade", "Try to get instance of database");
			synchronized (this)
			{
				Log.i ("selfmade", "Create new variable for storing current instance of database");
				var instance = INSTANCE;

				if (instance == null)
				{
					Log.i ("selfmade", "Create new instance of database");
					instance = Room.databaseBuilder (   context.applicationContext,
														PfandfinderDatabase::class.java,
														name = "pfandfinder_database.db")  .fallbackToDestructiveMigration ()
																						.allowMainThreadQueries ()
																						.createFromAsset ("database/pfandfinder_database__initial.db")
																						.build ();

					INSTANCE = instance;
				}

				Log.i ("selfmade", "Return instance of database");
				return instance;
			}
		}
	}
}