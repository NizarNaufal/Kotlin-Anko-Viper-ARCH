package id.nizardev.viperarchitecture

interface SplashContract {
    interface View {
        fun finishView()
    }

    interface Presenter {
        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }
}
