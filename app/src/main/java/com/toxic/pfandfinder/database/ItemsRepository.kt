package com.toxic.pfandfinder.database

// Layer 1
interface ItemsRepository
{
	fun getBottle (barcode: String): DatabaseTable__bottle?

	fun getStores (barcode: String): List<Store?>

	fun getInfo (): List<BottleInfo?>

	fun bottleExist (barcode: String): Int

	suspend fun insert (bottle: DatabaseTable__bottle)

	suspend fun update (bottle: DatabaseTable__bottle)

	suspend fun delete (bottle: DatabaseTable__bottle)
}