package id.nizardev.viperarchitecture.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import id.nizardev.viperarchitecture.BaseApplication
import id.nizardev.viperarchitecture.DetailContract
import id.nizardev.viperarchitecture.R
import id.nizardev.viperarchitecture.presenter.DetailPresenter
import id.nizardev.viperarchitecture.view.model.Joke
import org.jetbrains.anko.toast
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command

class DetailActivity : BaseActivity(), DetailContract.View {

    companion object {
        val TAG = "DetailActivity"
    }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Back) {
                    back()
                }
            }

            private fun back() {
                finish()
            }
        }
    }

    private var presenter: DetailContract.Presenter? = null
    private val toolbar: Toolbar by lazy { findViewById(R.id.toolbar_toolbar_view) }
    private val tvId: TextView? by lazy { findViewById(R.id.tv_joke_id_activity_detail) }
    private val tvJoke: TextView? by lazy { findViewById(R.id.tv_joke_activity_detail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = DetailPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        // add back arrow to toolbar
        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        // load invoking arguments
        val argument = intent.getParcelableExtra<Joke>("data")
        argument?.let { presenter?.onViewCreated(it) }

        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                presenter?.backButtonClicked()
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun showJokeData(id: String, joke: String) {
        tvId?.text = id
        tvJoke?.text = joke
    }

    override fun showInfoMessage(msg: String) {
        toast(msg)
    }
}