package ishan.tutorial.shopper

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItem)


    @Update
    suspend fun updateItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)


    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): Flow<List<ShoppingItem>>


    @Query("UPDATE shopping_items SET is_checked = :isChecked WHERE id = :id")
    suspend fun updateItemChecked(id: Int, isChecked: Boolean)


}
