package com.example.testkoinretrofit.DI

import com.example.testkoinretrofit.network.ApiService
import com.example.testkoinretrofit.data.repository.PostRepository
import com.example.testkoinretrofit.data.repository.PostRepositoryImpl
import com.example.testkoinretrofit.presentation.MyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Koin 모듈 설정
val myModule = module {
    //ApiService 인터페이스의 구현체를 생성하는 과정, Koin이 관리하는 의존성이 ApiService 타입
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<PostRepository> { PostRepositoryImpl(get()) }

    viewModel { MyViewModel(get()) }
}