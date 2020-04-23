package com.firozanwar.mvvmbybilal.ui.quotes

import androidx.lifecycle.ViewModel
import com.firozanwar.mvvmbybilal.data.repository.QuotesRepository
import com.firozanwar.mvvmbybilal.util.lazyDeferred

class QuotesViewModel(private val quotesRepository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred {
        quotesRepository.getQuotes()
    }
}
