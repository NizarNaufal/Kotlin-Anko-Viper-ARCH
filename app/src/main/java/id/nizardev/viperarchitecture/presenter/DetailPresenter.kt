package id.nizardev.viperarchitecture.presenter

import id.nizardev.viperarchitecture.BaseApplication
import id.nizardev.viperarchitecture.DetailContract
import id.nizardev.viperarchitecture.view.model.Joke
import ru.terrakok.cicerone.Router


class DetailPresenter(private var view: DetailContract.View?) : DetailContract.Presenter {

  private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

  override fun backButtonClicked() {
    router?.exit()
  }

  override fun onViewCreated(joke: Joke) {
    view?.showJokeData(joke.id.toString(), joke.text)
  }

  override fun onDestroy() {
    view = null
  }
}