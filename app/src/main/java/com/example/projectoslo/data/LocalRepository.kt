package com.example.projectoslo.data

import com.example.projectoslo.cache.ArticleDao
import com.example.projectoslo.cache.ArticleEntity
import com.example.projectoslo.models.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val articleDao: ArticleDao
) {
    suspend fun insertArticleList(article: List<ArticleEntity>){
        articleDao.insertArticleList(article)
    }

    fun getArticleList(): Flow<List<ArticleEntity>>{
       return articleDao.getArticleList()
    }
}