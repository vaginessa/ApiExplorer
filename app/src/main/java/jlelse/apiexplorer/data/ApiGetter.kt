package jlelse.apiexplorer.data

import com.afollestad.bridge.Bridge
import jlelse.apiexplorer.models.Api
import jlelse.apiexplorer.models.ApiResponse
import java.util.*

class ApiGetter {

	private val cache = HashMap<String?, Api?>()

	fun getApis(forceReload: Boolean = false): Set<Api?> {
		if (forceReload || cache.isEmpty()) {
			try {
				Bridge.get("https://www.googleapis.com/discovery/v1/apis")
						.asClass(ApiResponse::class.java)
						?.let {
							if (!(it.items?.isEmpty() ?: true)) {
								for (api in it.items!!) {
									cache.put(api.id, api)
								}
							}
						}
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
		return mutableSetOf<Api?>().apply { addAll(cache.values) }
	}

	fun getApi(id: String? = null, index: Int? = null): Api? =
			if (id != null) cache[id]
			else if (index != null) cache.values.toList()[index]
			else null

}
