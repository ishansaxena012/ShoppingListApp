🛒 Shopping List App

A simple **Shopping List App** built using **Kotlin, Jetpack Compose, and Room Database** to help users manage their shopping items efficiently.

📌 Features
✅ Add, edit, and delete shopping items  
✅ Mark items as purchased using a **checkbox**  
✅ Stores data using **Room Database** (persists even after app restarts)  
✅ Uses **Jetpack Compose** for a modern UI  
✅ Uses **ViewModel and LiveData** for better state management  

🛠️ Tech Stack
-> **Kotlin** - Primary language  
-> **Jetpack Compose** - Modern UI toolkit  
-> **Room Database** - Local storage for shopping items  
-> **Coroutines & Flow** - Asynchronous programming  
-> **MVVM Architecture** - Ensures better code structure  
-> **Material 3 Components** - UI elements  

📷 Screenshots
![IMG-20250211-WA0060](https://github.com/user-attachments/assets/5f807ab5-f42a-4a16-b42b-b839d294fcd1)
![IMG-20250211-WA0061](https://github.com/user-attachments/assets/fa23116c-8b65-4967-8e82-56fe84bb4318)
![IMG-20250211-WA0062](https://github.com/user-attachments/assets/c15fbde7-44de-4563-a91c-d834bc14342a)
![IMG-20250211-WA0065](https://github.com/user-attachments/assets/dc9a69dd-30f7-4604-9f76-0597cfddc56c)
![IMG-20250211-WA0064](https://github.com/user-attachments/assets/9416c00b-de39-4378-812b-486b45289d22)
![IMG-20250211-WA0066](https://github.com/user-attachments/assets/025320b9-de80-4cae-954d-f682a5ab2f22



🚀 Installation
1. Clone the repository:
     git clone https://github.com/ishansaxena012/ShoppingListApp.git
   
2. Open the project in **Android Studio**
3. Build & run the app on an emulator or a physical device  

 📂 Project Structure

📂 ishan.tutorial.shopper
│-- 📂 data
│   │-- ShoppingItem.kt              # Data model
│   │-- ShoppingDao.kt               # Database DAO
│   │-- ShoppingDatabase.kt          # Room database setup
│-- 📂 ui
│   │-- ShoppingListApp.kt           # Main composable UI
│   │-- ShoppingListItem.kt          # UI for each shopping item
│   │-- ShoppingItemEditor.kt        # UI for adding/editing items
│-- 📂 viewmodel
│   │-- ShoppingViewModel.kt         # ViewModel for managing data
│   │-- ShoppingRepository.kt        # Repository for handling data operations
│   │-- ShoppingViewModelFactory.kt  # Factory for ViewModel
│-- MainActivity.kt                  # Entry point of the app


🤝 Contributing
Feel free to fork the project and make enhancements!  
Pull requests are welcome. 😊  

