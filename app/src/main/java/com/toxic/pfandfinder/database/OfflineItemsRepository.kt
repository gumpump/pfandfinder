package com.toxic.pfandfinder.database

// Layer 2
class OfflineItemsRepository (private val queries: DatabaseQueries) : ItemsRepository
{
	override fun getBottle (barcode: String): DatabaseTable__bottle? = queries.getBottle (barcode)

	override fun getStores (barcode: String): List<Store?> = queries.getStores (barcode)

	override fun getInfo(): List<BottleInfo?> = queries.getInfo ()

	override fun bottleExist(barcode: String): Int = queries.bottleExist (barcode)

	override suspend fun insert (bottle: DatabaseTable__bottle) = queries.insert (bottle)

	override suspend fun update (bottle: DatabaseTable__bottle) = queries.update (bottle)

	override suspend fun delete (bottle: DatabaseTable__bottle) = queries.delete (bottle)
}