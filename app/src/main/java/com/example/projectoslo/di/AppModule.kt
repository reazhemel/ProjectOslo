package com.example.projectoslo.di

import android.content.Context
import androidx.room.Room
import com.example.projectoslo.cache.ArticleDao
import com.example.projectoslo.cache.ArticleDatabase
import com.example.projectoslo.data.RemoteRepository
import com.example.projectoslo.data.PostService
import com.example.projectoslo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePostService(retrofit: Retrofit): PostService{
        return retrofit.create(PostService::class.java)
    }

    @Singleton
    @Provides
    fun providePostRepository(api: PostService): RemoteRepository = RemoteRepository(api)


    @Singleton
    @Provides
    fun providesArticleDatabase(
       @ApplicationContext context: Context
    ): ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java,
        "article_db"
    ).build()

    @Singleton
    @Provides
    fun providesArticleDao(database: ArticleDatabase): ArticleDao{
        return database.articleDao()
    }
}