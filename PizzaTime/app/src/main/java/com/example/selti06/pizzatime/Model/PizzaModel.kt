package com.example.selti06.pizzatime.Model

import android.arch.persistence.room.*
import android.arch.persistence.room.Query
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

@Entity(tableName = "pizzas")
data class Pizza(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "composition")
    var composition: String,
    @ColumnInfo(name = "amount")
    var amount: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface PizzaDao {
    @Query("Select * from pizzas")
    fun getAllPizzas() : List<Pizza>
    @Insert
    fun insert (pizza: Pizza)
}

@Database(entities = arrayOf(Pizza::class), version = 4)
abstract class PizzaDb : RoomDatabase(){
    abstract fun pizzaDao() : PizzaDao
}

data class Post(
    @SerializedName("telephone") var telephone: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("amount") var amount: Int? = null,
    @SerializedName("title") var title: String?
)

interface ApiEndpoint {

    @GET("pizzas/")
    fun getPizzas(): Call<List<Pizza>>

    @GET("pizzas/{id}")
    fun getPizza(@Path("id") id: Int): Call<List<Pizza>>


    @FormUrlEncoded
    @POST("posts/")
    fun createPost(@Field("telephone") telephone : Int,
                   @Field("name") name: String,
                   @Field("id") id: Int,
                   @Field("amount") amount: Int,
                   @Field("title") title: String): Call<Post>

    @POST("posts/")
    fun createPost(@Body post: Post): Call<Post>
}