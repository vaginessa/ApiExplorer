package jlelse.apiexplorer.models

import android.support.annotation.Keep
import com.afollestad.bridge.annotations.Body

@Keep
class Api {
	@Body
	var id: String? = null
	@Body
	var name: String? = null
	@Body
	var version: String? = null
	@Body
	var title: String? = null
	@Body
	var description: String? = null
	@Body
	var discoveryRestUrl: String? = null
	@Body
	var documentationLink: String? = null
}
