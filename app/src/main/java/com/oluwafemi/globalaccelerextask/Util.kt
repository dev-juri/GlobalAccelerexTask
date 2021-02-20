package com.oluwafemi.globalaccelerextask

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.oluwafemi.globalaccelerextask.domain.CardDetails
import com.oluwafemi.globalaccelerextask.network.NetworkResponse


fun NetworkResponse.asDomainModel(): CardDetails {
    return CardDetails(
        cardScheme = this.scheme,
        cardType = this.type,
        bank = this.bank.name,
        country = this.country.name,
        cardLengthNumber = this.number.length,
        prepaid = this.prepaid
    )
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}
fun View.makeGone() {
    this.visibility = View.GONE
}

/*Check if phone is connected*/
@RequiresApi(Build.VERSION_CODES.M)
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
    } else {
        val activeNetworlkInfo = connectivityManager.activeNetwork
        if (activeNetworlkInfo != null) {
            return true
        }

    }
    return false
}