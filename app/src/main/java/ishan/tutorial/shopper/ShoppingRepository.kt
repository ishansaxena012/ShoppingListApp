package ishan.tutorial.shopper

class ShoppingRepository(private val shoppingDao: ShoppingDao) {

    suspend fun insertItem(item: ShoppingItem) = shoppingDao.insertItem(item)

    suspend fun updateItem(item: ShoppingItem) = shoppingDao.updateItem(item)

    suspend fun deleteItem(item: ShoppingItem) = shoppingDao.deleteItem(item)

    fun getAllItems() = shoppingDao.getAllItems()

    suspend fun updateItemChecked(id: Int, isChecked: Boolean) {
        shoppingDao.updateItemChecked(id, isChecked)
    }
}

