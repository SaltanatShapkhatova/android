package com.example.selti06.pizzatime.Model

import android.arch.persistence.room.*
import android.arch.persistence.room.Query
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.*

@Entity(tableName = "pizzas")
data class Pizza(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "composition")
    var composition: String,
    @ColumnInfo(name = "amount")
    var amount: Int,
    @ColumnInfo(name = "price")
    var price: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "orders")
data class Order(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "composition")
    var composition: String,
    @ColumnInfo(name = "amount")
    var amount: Int,
    @ColumnInfo(name = "price")
    var price: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val uuid : String?,
    val email : String,
    val password : String,
    val name : String,
    val surname : String
)

@Dao
interface UserDao {

    @Query("Select * from users")
    fun getUsers () : Flowable<List<User>>

    @Insert
    fun insertUser (user : User)

    @Query("Delete from users")
    fun nukeTable ()
}

@Dao
interface OrderDao {
    @Query("Select * from orders")
    fun getAllOrders() : List<Order>
    @Insert
    fun insert (order: Order)
    @Query("delete from orders where id=:id")
    fun deleteOrder(id :Int)
}

@Dao
interface PizzaDao {
    @Query("Select * from pizzas")
    fun getAllPizzas() : List<Pizza>
    @Insert
    fun insert (pizza: Pizza)
}

@Database(entities = arrayOf(Pizza::class, Order::class, User::class), version = 6)
abstract class PizzaDb : RoomDatabase(){
    abstract fun pizzaDao() : PizzaDao
    abstract fun orderDao() : OrderDao
    abstract fun userDao() : UserDao
}

data class Post(
    @SerializedName("telephone") var telephone: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("amount") var amount: Int? = null,
    @SerializedName("title") var title: String?,
    @SerializedName("composition") var composition: String?
)

interface ApiEndpoint {

    @GET("pizzas/")
    fun getPizzas(): Call<List<Pizza>>

    @POST("cart/")
    fun createPost(@Body post: Post): Call<Post>
}