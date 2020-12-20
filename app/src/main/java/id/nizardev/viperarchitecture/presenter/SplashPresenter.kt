package id.nizardev.viperarchitecture.presenter

import id.nizardev.viperarchitecture.BaseApplication
import id.nizardev.viperarchitecture.SplashContract
import id.nizardev.viperarchitecture.view.MainActivity
import ru.terrakok.cicerone.Router

class SplashPresenter(private var view: SplashContract.View?): SplashContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onViewCreated() {
        router?.navigateTo(MainActivity.TAG)
        view?.finishView()
    }

    override fun onDestroy() {
        view = null
    }
}