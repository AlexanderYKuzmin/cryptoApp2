package com.example.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (

    @SerializedName("TYPE")
    @Expose
    val type: String?,


    @SerializedName("MARKET")
    @Expose
    val market: String?,


    @SerializedName("FROMSYMBOL")
    @Expose
    @PrimaryKey
    val fromSymbol: String,


    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,


    @SerializedName("FLAGS")
    @Expose
    val flags: String?,


    @SerializedName("PRICE")
    @Expose
    val price: Double?,


    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Int?,


    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: Double?,


    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastVolumeTo: Double?,


    @SerializedName("LASTTRADEID")
    @Expose
    val lastTradeId: String?,


    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: Double?,


    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumeDayTo: Double?,


    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double?,


    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourTo: Double?,


    @SerializedName("OPENDAY")
    @Expose
    val openDay: Double?,


    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double?,


    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double?,


    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: Double?,


    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: Double?,


    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: Double?,


    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,


    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumeHour: Double?,


    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumeHourTo: Double?,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val toptiervolume24hour: Double?,


    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    val toptiervolume24hourTo: Double?,


    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?

)
