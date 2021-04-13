package com.example.encoratask

class CharacterListPresenter(private val view: CharacterListMvp.View, private val model: CharacterListMvp.Model) : CharacterListMvp.Presenter {

    override fun init() {
        model.loadCharacters(view::showCharacters)
    }

    override fun onScrolledToBottom(currentCharacters: MutableList<Character>) {
        model.loadNextPage({ it?.let {
            val previousSize = currentCharacters.size
            currentCharacters.addAll(it)
            view.notifyCharactersAdded(previousSize, it.size)
            view.scrollToPosition(previousSize + 1)
        }})
    }
}