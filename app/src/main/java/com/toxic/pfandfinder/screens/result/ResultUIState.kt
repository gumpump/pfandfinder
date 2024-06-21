package com.toxic.pfandfinder.screens.result

import com.toxic.pfandfinder.database.Store

// Container for the data used by the app
data class ResultUIState (val barcode: String = "", val bottleName: String = "", val stores: List<Store> = listOf (), val found: Boolean = false)