package com.example.jetreader.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.jetreader.R
import com.example.jetreader.screens.home.SearchViewModel
import com.example.jetreader.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchViewModel: SearchViewModel
) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight(0.075f)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        IconButton(onClick = {
            searchViewModel.hideSearchContents()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = searchViewModel.searchText,
            onValueChange = { searchViewModel.updateSearchText(it) },
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            textStyle = TextStyle(fontWeight = FontWeight.Bold),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (searchViewModel.isSearchQueryValid() == false) Toast.makeText(
                        context, "Field can't be empty!", Toast.LENGTH_SHORT
                    ).show()
                    else keyboard?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            leadingIcon = {
                IconButton(
                    onClick = {
                        if (searchViewModel.isSearchQueryValid() == false) Toast.makeText(
                            context, "Field can't be empty!", Toast.LENGTH_SHORT
                        ).show()
                        else keyboard?.hide()
                    },
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.5f),
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search",
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { searchViewModel.clearSearchText() }) {
                    Icon(
                        imageVector = Icons.Default.Close, contentDescription = "Close Search Box",
                        modifier = Modifier.fillMaxSize(0.5f)
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = Color.Transparent
            )
        )
    }
}