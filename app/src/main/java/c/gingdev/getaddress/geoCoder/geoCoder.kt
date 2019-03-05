package c.gingdev.getaddress.geoCoder

import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class geoCoder {
	fun urlBuilder(latlon: Pair<Double?, Double?>): String
			= "https://maps.googleapis.com/maps/api/geocode/json?latlng=${latlon.first},${latlon.second}&language=ko&key=AIzaSyD5WPt_upaLFTw0UE7Uc8kbFwYwRk5gG7M"

	fun downloadUrl(url: String): String? {
		var data: String = ""
		var ins: InputStream? = null
		var urlConn: HttpsURLConnection? = null
		try {
			val urls = URL(url)
			urlConn = urls.openConnection() as HttpsURLConnection
			urlConn.connect()

			ins = urlConn.inputStream
			val br = BufferedReader(InputStreamReader(ins))
			val sb = StringBuffer()

			var line: String? = null
			while ({ line = br.readLine(); line}() != null) {
				sb.append(line)
			}

			data = sb.toString()
			br.close()
		} catch (e: Exception) {
			return null
		} finally {
			Log.d("urlend", "end")
			ins?.close()
			urlConn?.disconnect()
		}
		return data
	}
}