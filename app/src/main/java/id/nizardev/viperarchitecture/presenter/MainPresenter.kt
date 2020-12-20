package id.nizardev.viperarchitecture.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.nizardev.viperarchitecture.BaseApplication
import id.nizardev.viperarchitecture.MainContract
import id.nizardev.viperarchitecture.view.DetailActivity
import id.nizardev.viperarchitecture.view.model.Joke
import ru.terrakok.cicerone.Router
import com.github.kittinunf.result.Result
import id.nizardev.viperarchitecture.iteractor.MainInteractor

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter, MainContract.InteractorOutput {

    private var interactor: MainContract.Interactor? = MainInteractor()
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun listItemClicked(joke: Joke?) {
        router?.navigateTo(DetailActivity.TAG, joke)
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadJokesList { result ->
            when (result) {
                is Result.Failure -> {
                    this.onQueryError()
                }
                is Result.Success -> {
                    val jokesJsonObject = result.get().obj()

                    val type = object : TypeToken<List<Joke>>() {}.type
                    val jokesList: List<Joke> =
                        Gson().fromJson(jokesJsonObject.getJSONArray("value").toString(), type)

                    this.onQuerySuccess(jokesList)
                }
            }
        }
    }

    override fun onQuerySuccess(data: List<Joke>) {
        view?.hideLoading()
        view?.publishDataList(data)
    }

    override fun onQueryError() {
        view?.hideLoading()
        view?.showInfoMessage("Error when loading data")
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}