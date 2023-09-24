package com.example.paging3jetpackcompose.screen.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.paging3jetpackcompose.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchedClicked: (String) -> Unit,
    onClosedClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics {
                contentDescription = "Search Widget"
            },
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
            ,
            value = text,
            onValueChange = {onTextChange(it)},
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = "Search here",
                    color = Color.White
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search ,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    onClosedClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close ,
                        contentDescription = "Close Search"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )
            ,
            keyboardActions = KeyboardActions(
                onSearch = {onSearchedClicked(text)}
            )
        )

    }
    
}