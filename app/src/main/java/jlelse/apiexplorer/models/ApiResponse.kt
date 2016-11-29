package jlelse.apiexplorer.models

import android.support.annotation.Keep
import com.afollestad.bridge.annotations.Body

@Keep
class ApiResponse {
	@Body
	var items: Array<Api>? = null
}
