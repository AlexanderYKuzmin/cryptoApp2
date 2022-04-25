package com.example.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.db.AppDatabase
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.example.cryptoapp.pojo.CoinPriceInfoData
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinViewModel(application: Application): AndroidViewModel(application) {

    private val LOG_TAG = "TEST_OF_LOADING_DATA"

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()


    fun loadData() {
        val disposable: Disposable = ApiFactory.apiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fsyms = it.toString()) }
            .map { getCoinPriceListFromRawData(it) }
            .subscribeOn(Schedulers.io())
            .subscribe( {
                if (it != null) {
                    db.coinPriceInfoDao().insertPriceList(it)
                }

            }, {
                it.message?.let { it1 -> Log.d(LOG_TAG, it1) }
            })
        compositeDisposable.add(disposable)
    }

    private fun getCoinPriceListFromRawData(coinPriceInfoData: CoinPriceInfoData): List<CoinPriceInfo>? {
        val jsonObject = coinPriceInfoData.coinPriceJsonObject ?: return null
        val result = ArrayList<CoinPriceInfo>()
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey), CoinPriceInfo::class.java)
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}