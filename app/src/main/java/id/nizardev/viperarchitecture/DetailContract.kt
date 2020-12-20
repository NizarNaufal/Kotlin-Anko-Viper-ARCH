package id.nizardev.viperarchitecture

import id.nizardev.viperarchitecture.view.model.Joke


interface DetailContract {
    interface View {
        fun showJokeData(id: String, joke: String)
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun backButtonClicked()

        // Model updates
        fun onViewCreated(joke: Joke)

        fun onDestroy()
    }
}
