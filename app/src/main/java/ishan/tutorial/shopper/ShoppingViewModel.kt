package ishan.tutorial.shopper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    val shoppingItems: Flow<List<ShoppingItem>> = repository.getAllItems()

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            val newItem = ShoppingItem(name = name, quantity = quantity, isChecked = false)
            repository.insertItem(newItem)
        }
    }

    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun updateItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun toggleItemChecked(item: ShoppingItem) {
        viewModelScope.launch {
            repository.updateItem(item.copy(isChecked = !item.isChecked))
        }
    }

    fun updateShoppingList(newList: List<ShoppingItem>) {
        viewModelScope.launch {
            newList.forEach { item ->
                repository.updateItem(item)
            }
        }
    }
}
