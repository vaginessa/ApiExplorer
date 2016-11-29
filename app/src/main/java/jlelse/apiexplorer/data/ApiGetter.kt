package jlelse.apiexplorer.data

import com.afollestad.bridge.Bridge

import java.util.HashMap
import java.util.HashSet

import jlelse.apiexplorer.models.Api
import jlelse.apiexplorer.models.ApiResponse

class ApiGetter {

	private val cache = HashMap<String?, Api?>()

	fun getApis(forceReload: Boolean): Set<Api?> {
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

}
