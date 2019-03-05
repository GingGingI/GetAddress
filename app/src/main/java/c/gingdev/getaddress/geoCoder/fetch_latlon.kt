package c.gingdev.getaddress.geoCoder

import android.os.AsyncTask
import org.json.JSONObject
import java.lang.Exception

class fetch_latlon(listener: fetchListener): AsyncTask<String, String, String?>() {
	val listener = listener
	override fun doInBackground(vararg param: String?): String? {
		var data: String? = null
		try {
			data = geoCoder.downloadUrl(param[0]!!)
		}catch (e: Exception) {
			e.printStackTrace()
		}
		return data
	}

	override fun onPostExecute(result: String?) {
		super.onPostExecute(result)
		var data = result
		if (data?.isNotEmpty()!!) {
			val jObj = JSONObject(data)
			val jResult = jObj.getJSONArray("results")
			data = ""
			for (i in 0 until 2) {
				data +=	(jResult[i] as JSONObject).get("formatted_address").toString() + "|"
			}
		}

		listener.onFetchFinished(data)
	}

	private val geoCoder = geoCoder()

	interface fetchListener {
		fun onFetchFinished(data: String?)
	}
}
