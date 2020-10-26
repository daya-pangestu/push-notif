package com.daya.taha.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Database(
    entities =  [Placeholder::class],
    version = 1,
    exportSchema = false
)
abstract class TahaDb :RoomDatabase() {

    //dao
    abstract fun placeholderDao() : PlaceholderDao

}

@Entity
data class Placeholder(
    @PrimaryKey
    val id : Int
)

@Dao
interface PlaceholderDao {
    @Query("SELECT * FROM placeholder")
    fun getall() : LiveData<List<Placeholder>>
}