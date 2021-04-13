package com.example.encoratask

class CharacterListPresenter(val view: CharacterListMvp.View, val model: CharacterListMvp.Model) : CharacterListMvp.Presenter {

    override fun init() {
        model.loadCharacters(object: CharacterListMvp.Callback<List<Character>?> {
            override fun call(param: List<Character>?) {
                view.showCharacters(param)
            }
        }, null)
    }

    override fun onScrolledToBottom(currentCharacters: MutableList<Character>) {
        model.loadNextPage(object: CharacterListMvp.Callback<List<Character>?> {
            override fun call(param: List<Character>?) {
                param?.let {
                    val previousSize = currentCharacters.size
                    currentCharacters.addAll(it)
                    view.notifyCharactersAdded(previousSize, it.size)
                    view.scrollToPosition(previousSize + 1)
                }

            }
        }, null)
    }
}