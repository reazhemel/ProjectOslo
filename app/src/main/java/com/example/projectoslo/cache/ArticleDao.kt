package com.example.projectoslo.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

 @Insert
 suspend fun insertArticleList(article:List<ArticleEntity>)

 @Query("SELECT * FROM article_table")
 fun getArticleList(): Flow<List<ArticleEntity>>

}