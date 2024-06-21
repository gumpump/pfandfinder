package com.toxic.pfandfinder.screens.add

import com.toxic.pfandfinder.database.BottleInfo

data class AddUIState (val bottles: List<BottleInfo> = listOf (), val found: Boolean = false)