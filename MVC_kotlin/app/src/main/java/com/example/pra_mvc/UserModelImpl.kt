package com.example.pra_mvc

class UserModelImpl : UserModel {
    override fun getUser(): User {
        // 데이터를 생성하거나 가져오는 로직을 구현
        // 보통은 백엔드와 통신을 해서 데이터를 가져오는 부분을 구현할 것이다!
        return User(id = 1, name = "근자")
    }
}