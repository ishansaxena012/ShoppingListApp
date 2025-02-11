package ishan.tutorial.shopper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import ishan.tutorial.shopper.ui.theme.ShopperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val database = ShoppingDatabase.getDatabase(this)
        val repository = ShoppingRepository(database.shoppingDao())
        val viewModelFactory = ShoppingViewModelFactory(repository)
        val viewModel: ShoppingViewModel = ViewModelProvider(this, viewModelFactory)[ShoppingViewModel::class.java]

        setContent {
            ShopperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShoppingListApp(viewModel)
                }
            }
        }
    }
}
