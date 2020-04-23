package com.firozanwar.mvvmbybilal.data.network.responses

import com.firozanwar.mvvmbybilal.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)