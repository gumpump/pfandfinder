package com.toxic.pfandfinder

import com.toxic.pfandfinder.database.Store

// Container for the informations shared by most screens
data class MainUIState (val bottleLabel: String = "",
                        val bottleType: String = "",
                        val bottleBarcode: String = "",
						val stores: List<Store> = listOf ())