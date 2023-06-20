package com.example.projectoslo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectoslo.cache.ArticleEntity
import com.example.projectoslo.data.LocalRepository
import com.example.projectoslo.data.RemoteRepository
import com.example.projectoslo.models.NewsApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<ArticleEntity>>(emptyList())
    val articles = _articles.asStateFlow()

    init {
        getNewsFromApi()
        getArticlesFromLocal()
    }

    private fun getNewsFromApi() {
        viewModelScope.launch {
            remoteRepository.getPosts()
                .flowOn(Dispatchers.Main)
                .catch { exception ->

                }
                .collect { news->
                    news.articles?.let {
                        localRepository.insertArticleList(
                            news.articles.map {
                                ArticleEntity(
                                    author = it.author ?:"",
                                    title = it.title ?:"",
                                    description = it.description ?:""
                                )
                            }
                        )
                    }
                }
        }

    }
    private fun getArticlesFromLocal(){
        viewModelScope.launch {
            localRepository.getArticleList().collect {
                _articles.value = it
            }
        }

    }
}