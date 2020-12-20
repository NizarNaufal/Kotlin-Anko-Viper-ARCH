package id.nizardev.viperarchitecture.iteractor

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import id.nizardev.viperarchitecture.MainContract


class MainInteractor : MainContract.Interactor {

    companion object {
        val icndbUrl = "https://api.icndb.com/jokes"
    }

    override fun loadJokesList(interactorOutput: (result: Result<Json, FuelError>) -> Unit) {
        icndbUrl.httpPost().responseJson { _, _, result ->
            interactorOutput(result)
        }
    }
}