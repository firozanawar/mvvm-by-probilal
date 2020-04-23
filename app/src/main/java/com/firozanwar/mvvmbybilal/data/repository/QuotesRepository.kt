package com.firozanwar.mvvmbybilal.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firozanwar.mvvmbybilal.data.db.AppDatabase
import com.firozanwar.mvvmbybilal.data.db.entities.Quote
import com.firozanwar.mvvmbybilal.data.network.MyApi
import com.firozanwar.mvvmbybilal.data.network.SafeApiRequest
import com.firozanwar.mvvmbybilal.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private suspend fun fetchQuotes() {

        if (isFetchNeeded()) {
            val response = apiRequest { myApi.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            db.getQuoteDao().getQuotes()
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    private fun saveQuotes(quotes: List<Quote>) {

        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

}