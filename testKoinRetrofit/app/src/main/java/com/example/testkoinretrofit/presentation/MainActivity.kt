package com.example.testkoinretrofit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testkoinretrofit.R
import com.example.testkoinretrofit.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 클릭 시 데이터 가져오기
        binding.btn1.setOnClickListener {
            viewModel.fetchPost()
        }

        // LiveData를 관찰하여 데이터가 변경될 때 UI를 업데이트
        viewModel.post.observe(this, { post ->
            // post가 null이 아닐 때 UI 업데이트
            post?.let {
                binding.tv1.text = "User ID: ${it.userId}\n" +
                        "ID: ${it.id}\n" +
                        "Title: ${it.title}\n" +
                        "Body: ${it.body}"
            }
        })
    }
}