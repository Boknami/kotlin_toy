package com.example.pra_mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var usernameTextView: TextView
    private lateinit var userIDTextView: TextView

    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View에 선언되있는 값이랑 매칭(초기화해두기)
        usernameTextView = findViewById(R.id.txt_name)
        userIDTextView = findViewById(R.id.txt_ID)


        // UserModel 구현체를 생성 또는 주입받음
        userModel = UserModelImpl()

        // Model의 데이터를 View에 설정
        val user = userModel.getUser()

        // Model이 가진 데이터를 View에 설정해주자
        usernameTextView.text = "유저의 이름 :  ${user.name}!"
        userIDTextView.text = "유저의 ID : ${user.id}"
    }
}