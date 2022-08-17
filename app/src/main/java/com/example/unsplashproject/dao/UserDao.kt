package com.example.unsplashproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.unsplashproject.model.login.UserModel

@Dao
interface UserDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserModel)

    @Update
    suspend fun update(user: UserModel)

    @Query("SELECT * FROM loginInfo ORDER BY id DESC")
    fun getAllUserDetail(): LiveData<List<UserModel>>

    @Query("SELECT * FROM loginInfo WHERE email LIKE :email")
    fun getUser(email: String): LiveData<UserModel?>

    @Query("SELECT EXISTS(SELECT * FROM loginInfo WHERE email =:email)")
    fun isUserExist(email: String): LiveData<Boolean>

    @Query("SELECT * FROM loginInfo WHERE id LIKE :id")
    fun getUserById(id: Int): LiveData<UserModel?>

    @Query("UPDATE loginInfo SET firstName = :firstName WHERE id=:id")
    suspend fun updateFirstName(firstName: String, id: Int)

    @Query("UPDATE loginInfo SET lastName = :lastName WHERE id=:id")
    suspend fun updateLastName(lastName: String, id: Int)

    @Query("UPDATE loginInfo SET email = :email WHERE id=:id")
    suspend fun updateEmail(email: String, id: Int)

    @Query("UPDATE loginInfo SET password = :password WHERE id=:id")
    suspend fun updatePassword(password: String, id: Int)

    @Query("UPDATE loginInfo SET image = :imageUri WHERE id=:id")
    suspend fun updateImage(imageUri: String, id: Int)
}