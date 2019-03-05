package c.gingdev.getaddress

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

open class GPS: AppCompatActivity(), LocationListener {
	private var location: Location? = null

	private lateinit var context: Context
	private lateinit var listener: locationListener
	private lateinit var locationManager: LocationManager

	protected fun setContext(context: Context) {
		this.context = context
	}

	protected fun listener(listener: locationListener) {
		this.listener = listener
	}

	protected fun getLocation() {
		locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

		location = getLastKnownLocation()
		val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
		val isNETWORKEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

		if (!isGPSEnabled && !isNETWORKEnabled) {
			listener.failedToGetLocation(Exception(BothProviderNotEnabled))
		} else {
			if (isGPSEnabled) {
				getLocations(isGPS)
			}
			if (isNETWORKEnabled) {
				getLocations(isNETWORK)
			}
		}
	}

	@SuppressLint("MissingPermission")
	private fun getLocations(provider: Int) {
		when (provider) {
			isGPS -> {
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						MIN_TIME_BW_UPDATE,
						MIN_DISTANCE_CAHNGE_FOR_UPDATES,
						this)
			}
			isNETWORK -> {
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
						MIN_TIME_BW_UPDATE,
						MIN_DISTANCE_CAHNGE_FOR_UPDATES,
						this)
			}
		}
		locationManager.notNull {
			location.notNull {
				listener.successToGetLocation(Pair(location?.latitude, location?.longitude))
			}
		}
	}

	@SuppressLint("MissingPermission")
	private fun getLastKnownLocation(): Location? {
		locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

		val providers: List<String> = locationManager.allProviders
		var bestLocation: Location? = null

		for (provider in providers) {
			val location = locationManager.getLastKnownLocation(provider)
			location.notNull {
				if (bestLocation == null || location.accuracy < bestLocation!!.accuracy) {
					bestLocation = location
				}
			}
		}
		return bestLocation
	}

	override fun onLocationChanged(location: Location?) {
		this.location = location
	}
	override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
	}
	override fun onProviderEnabled(p0: String?) {
	}
	override fun onProviderDisabled(p0: String?) {
	}

	protected fun Any?.notNull(f: ()-> Unit) {
		if (this != null) f()
	}
	protected fun Any?.isNull(f: ()-> Unit) {
		if (this == null) f()
	}

	companion object {
		private val isGPS = 0
		private val isNETWORK = 1

		private val MIN_TIME_BW_UPDATE: Long = 0
		private val MIN_DISTANCE_CAHNGE_FOR_UPDATES: Float = 0f

		private val BothProviderNotEnabled = "Both providers(GPS, NETWORK) are not enabled."
	}

	interface locationListener {
		fun successToGetLocation(latlon: Pair<Double?, Double?>)
		fun failedToGetLocation(e: Exception)
	}
}