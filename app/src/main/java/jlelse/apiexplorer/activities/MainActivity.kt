package jlelse.apiexplorer.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.mcxiaoke.koi.async.asyncSafe
import com.mcxiaoke.koi.async.mainThreadSafe
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import jlelse.apiexplorer.R
import jlelse.apiexplorer.adapters.ApiItem
import jlelse.apiexplorer.data.ApiGetter

class MainActivity : AppCompatActivity() {

	private val apiGetter = ApiGetter()
	private var progressDialog: MaterialDialog? = null
	private val fastAdapter = FastItemAdapter<ApiItem>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		(findViewById(R.id.recycler_view) as RecyclerView?)?.apply {
			layoutManager = LinearLayoutManager(this@MainActivity)
			adapter = fastAdapter
		}
		progressDialog = MaterialDialog.Builder(this)
				.content(R.string.loading)
				.progress(true, 0)
				.show()
		asyncSafe {
			val response = apiGetter.getApis(false)
			mainThreadSafe {
				progressDialog?.dismiss()
				fastAdapter.setNewList(response.mapTo(mutableListOf<ApiItem>()) { ApiItem().withApi(it) })
			}
		}
	}
}
