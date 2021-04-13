package com.example.encoratask

interface CharacterListMvp {

    interface View {
        fun showCharacters(characters : List<Character>?)
        fun notifyCharactersAdded(from: Int, size: Int)
        fun scrollToPosition(position: Int)
    }

    interface Model {
        fun loadCharacters(callback : (List<Character>?) -> Unit, errorCallback : (Throwable?) -> Unit = {})
        fun loadNextPage(callback : (List<Character>?) -> Unit, errorCallback : (Throwable?) -> Unit = {})
    }

    interface Presenter {
        fun init()
        fun onScrolledToBottom(currentCharacters : MutableList<Character>)
    }
}