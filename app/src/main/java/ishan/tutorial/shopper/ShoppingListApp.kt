package ishan.tutorial.shopper

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShoppingListApp(viewModel: ShoppingViewModel) {
    val sItems by viewModel.shoppingItems.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Box {
        Image(
            painter = painterResource(id = R.drawable.backgroundnotes1),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .size(width = 199.dp, height = 48.dp)
                    .padding(horizontal = 0.01.dp, vertical = 1.3.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDFAEA9)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Add Item",
                    color = Color(0xFF5A403C),
                    fontSize = 18.sp
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(sItems) { item ->
                    if (item.isEditing) {
                        ShoppingItemEditor(
                            item = item,
                            onEditComplete = { editedName, editedQuantity ->
                                viewModel.updateItem(
                                    item.copy(name = editedName, quantity = editedQuantity, isEditing = false)
                                )
                            }
                        )
                    } else {
                        ShoppingListItem(
                            item = item,
                            onEditClick = { viewModel.updateItem(item.copy(isEditing = true)) },
                            onDeleteClick = { viewModel.deleteItem(item) },
                            onCheckedChange = { isChecked -> viewModel.updateItem(item.copy(isChecked = isChecked)) }
                        )
                    }
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    modifier = Modifier
                        .padding(16.dp),
                    title = {
                        Text(
                            text = "Add Shopping Item",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5A403C),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    },
                    text = {
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            OutlinedTextField(
                                value = itemName,
                                onValueChange = { itemName = it },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text("Enter the name", color = Color(0xFF00796B)) },
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color(0xFF424242),
                                    fontWeight = FontWeight.Medium
                                )
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = itemQuantity,
                                onValueChange = { itemQuantity = it },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text("Enter the Quantity", color = Color(0xFF1565C0)) },
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color(0xFF424242),
                                    fontWeight = FontWeight.Medium
                                )
                            )

                        }
                    },
                    confirmButton = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    if (itemName.isNotBlank()) {
                                        viewModel.addItem(itemName, itemQuantity.toIntOrNull() ?: 1)
                                        showDialog = false
                                        itemName = ""
                                        itemQuantity = ""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("ADD", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Button(
                                onClick = { showDialog = false },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),                                 shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("CANCEL", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    containerColor = Color(0xFFFAFAFA)
                )
            }

        }
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (item.isChecked) Color(0xFFD3EBCD) else Color(0xFFF5F5F5),
        label = ""
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(2.dp, Color(0xFF002366), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = { onCheckedChange(it) },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF4CAF50),
                uncheckedColor = Color(0xFFB0BEC5),
                checkmarkColor = Color.White
            ),
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(6.dp))
                .border(2.dp, if (item.isChecked) Color(0xFF2E7D32) else Color(0xFFB0BEC5))
                .background(if (item.isChecked) Color(0xFFE8F5E9) else Color.White)
                .clickable { onCheckedChange(!item.isChecked) }
                .padding(2.dp)
        )


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = item.name,
                color = if (item.isChecked) Color.Gray else Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = if (item.isChecked) TextDecoration.LineThrough else TextDecoration.None
            )
            Text(
                text = "Qty: ${item.quantity}",
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
        }

        Row {
            IconButton(
                onClick = onEditClick,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFDFAEA9), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF002366), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ShoppingItemEditor(
    item: ShoppingItem,
    onEditComplete: (String, Int) -> Unit
) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = editedName,
            onValueChange = { editedName = it },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = editedQuantity,
            onValueChange = { editedQuantity = it },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002366))
        ) {
            Text(text = "SAVE", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}
