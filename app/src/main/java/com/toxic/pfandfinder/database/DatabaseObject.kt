package com.toxic.pfandfinder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bottle")
data class DatabaseTable__bottle (  @PrimaryKey val id: Int,
                                    val barcode: String,
									val fullname: String)

@Entity(tableName = "store")
data class DatabaseTable__store (   @PrimaryKey val id: Int,
									val fullname: String,
									val imagecode: Int)

@Entity(tableName = "bottle_store")
data class DatabaseTable__bottle_store (@PrimaryKey val id: Int,
										val bottle_id: Int,
										val store_id: Int)

data class Store (@ColumnInfo(name = "fullname") val name: String?,
                  @ColumnInfo(name = "imagecode") val imagecode: Int?)

data class BottleInfo ( @ColumnInfo(name = "fullname") val name: String?,
						@ColumnInfo(name = "barcode") val barcode: String?,
						@ColumnInfo(name = "storecount") val storecount: Int)