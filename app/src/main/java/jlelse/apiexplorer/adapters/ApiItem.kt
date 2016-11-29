package jlelse.apiexplorer.adapters

import android.support.annotation.Keep
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.mikepenz.fastadapter.items.AbstractItem

import jlelse.apiexplorer.R
import jlelse.apiexplorer.models.Api

@Keep
class ApiItem : AbstractItem<ApiItem, ApiItem.ViewHolder>() {
	private var api: Api? = null

	fun withApi(api: Api?): ApiItem {
		this.api = api
		return this
	}

	override fun getType() = R.id.api_item

	override fun getLayoutRes() = R.layout.api_item

	override fun bindView(viewHolder: ViewHolder, payloads: List<Any>?) {
		super.bindView(viewHolder, payloads)
		viewHolder.title.text = api?.title
		viewHolder.description.text = api?.description
	}

	override fun unbindView(holder: ViewHolder?) {
		super.unbindView(holder)
		holder?.title?.text = null
		holder?.description?.text = null
	}

	@Keep
	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		var title: TextView
		var description: TextView

		init {
			this.title = view.findViewById(R.id.api_title) as TextView
			this.description = view.findViewById(R.id.api_description) as TextView
		}
	}
}