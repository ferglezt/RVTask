package com.example.encoratask

interface CharacterListMvp {

    interface View {
        fun showCharacters(characters : List<Character>?)
        fun notifyCharactersAdded(from: Int, size: Int)
        fun scrollToPosition(position: Int)
    }

    interface Model {
        fun loadCharacters(callback : Callback<List<Character>?>, errorCallback : Callback<Throwable>?)
        fun loadNextPage(callback : Callback<List<Character>?>, errorCallback : Callback<Throwable>?)
    }

    interface Presenter {
        fun init()
        fun onScrolledToBottom(currentCharacters : MutableList<Character>)
    }

    interface Callback<T> {
        fun call(param: T)
    }
}