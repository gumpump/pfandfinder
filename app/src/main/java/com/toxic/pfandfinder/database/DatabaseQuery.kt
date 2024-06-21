package com.toxic.pfandfinder.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// Layer 0
@Dao
interface DatabaseQueries
{
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert (bottle: DatabaseTable__bottle)

	@Update
	suspend fun update (bottle: DatabaseTable__bottle)

	@Delete
	suspend fun delete (bottle: DatabaseTable__bottle)

	@Query("SELECT * FROM bottle WHERE barcode = :barcode")
	fun getBottle (barcode: String): DatabaseTable__bottle

	@Query("SELECT store.fullname, store.imagecode FROM bottle INNER JOIN bottle_store ON bottle.id = bottle_store.bottle_id INNER JOIN store ON bottle_store.store_id = store.id WHERE bottle.barcode = :barcode")
	fun getStores (barcode: String): List<Store>

	@Query("SELECT bottle.fullname, bottle.barcode, COUNT(store.id) AS storecount FROM bottle INNER JOIN bottle_store ON bottle.id = bottle_store.bottle_id INNER JOIN store ON bottle_store.store_id = store.id GROUP BY bottle.fullname")
	fun getInfo (): List<BottleInfo>

	@Query("SELECT COUNT(bottle.id) FROM bottle WHERE bottle.barcode = :barcode")
	fun bottleExist (barcode: String): Int
}