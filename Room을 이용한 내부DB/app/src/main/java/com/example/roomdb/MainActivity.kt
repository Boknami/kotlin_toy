package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

//엔티티(테이블 생성!)
@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?
)

//다오(DB와 상호작용(CRUD)
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inf = findViewById<TextView>(R.id.txt)

        //데이터베이스 인스턴스 생성
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "testDB-230826"
        ).build()

        //DAO인스턴스 가져오기..이제 사용하면 된다!
        val userDao = db.userDao()

        val user1 = User(uid = 1, firstName = "신", lastName = "1")
        val user2 = User(uid = 2, firstName = "신", lastName = "2")
        val user3 = User(uid = 3, firstName = "신", lastName = "3")

        // 액티비티의 lifecycleScope 내에서 코루틴 사용
        lifecycleScope.launch(Dispatchers.IO) {
            // IO 스레드에서 사용자 삽입
//            userDao.insertAll(user1)
               userDao.insertAll(user2)
               userDao.insertAll(user3)

            // IO 스레드에서 사용자 검색
            val users: List<User> = userDao.getAll()

            // 메인 스레드에서 UI 업데이트
            launch(Dispatchers.Main) {
                Log.d("테스트", users.toString())
                inf.text = users.toString()
            }
        }
//        userDao.insertAll()
//        //현재 전체 DB 출력
//        val users: List<User> = userDao.getAll()
//        Log.d("테스트", users.toString())
//        inf.setText(users.toString())
    }
}