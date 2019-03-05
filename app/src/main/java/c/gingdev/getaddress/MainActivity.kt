package c.gingdev.getaddress

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import c.gingdev.getaddress.geoCoder.fetch_latlon
import c.gingdev.getaddress.geoCoder.geoCoder
import c.gingdev.getaddress.permissions.permissionChecker
import c.gingdev.getaddress.permissions.utils.permissionListener
import c.gingdev.getaddress.permissions.values.permissionsList
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.content.Context.CLIPBOARD_SERVICE
import androidx.core.content.ContextCompat.getSystemService



class MainActivity : GPS(), View.OnClickListener {

	private lateinit var permissionChk: permissionChecker

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		gpsInit()
		viewInit()
	}

	private fun gpsInit() {
		permissionChk = permissionChecker(this, permissionsList.locationPermissionList ,object : permissionListener {
			override fun permissionGranted() {
				setContext(this@MainActivity)
				listener(object : GPS.locationListener {
					override fun successToGetLocation(latlon: Pair<Double?, Double?>) {
						val geoCoder = geoCoder()
						fetch_latlon(object : fetch_latlon.fetchListener {
							override fun onFetchFinished(data: String?) {
								data.notNull {
									for (i in 0 until data?.split("|")!!.size)
										when(i) {
											0 -> {
												fstView.text = data.split("|")[i]
												fstView.visibility = View.VISIBLE
											}
											1 -> {
												SecView.text = data.split("|")[i]
												SecView.visibility = View.VISIBLE
											} }
								}
							}
						}).execute(geoCoder.urlBuilder(latlon))
					}
					override fun failedToGetLocation(e: Exception) {
						Log.e("location","failed to get location as -> ${e.message}")
					}
				})
				getLocation()
			}
			override fun permissionDenied() {
				Log.e("permission", "Denied")
			}
		})
	}
	private fun viewInit() {
		Map.setOnClickListener(this)
		findAsGps.setOnClickListener(this)
		fstView.setOnClickListener(this)
		SecView.setOnClickListener(this)
	}

	override fun onClick(v: View?) {
		when(v) {
			Map -> {
				IntentToMap()
			}
			findAsGps -> {
				permissionChk.requestPermission()
			}
			fstView,
			SecView -> {
				val clipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
				val clipdata: ClipData = ClipData.newPlainText("Address",(v as TextView).text)
				clipboardManager.primaryClip = clipdata

				Toast.makeText(this, "주소 복사 :${v.text}", Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun IntentToMap() {
		val i = Intent(this, MapsActivity::class.java)
		startActivity(i)
		finish()
	}
}
