package com.example.jetreader.screens.login

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.components.CustomButton
import com.example.jetreader.components.InputField
import com.example.jetreader.components.InputViewModel
import com.example.jetreader.utils.AppConstants

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    inputViewModel: InputViewModel = InputViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.scrollable(
                        rememberScrollState(),
                        orientation = Orientation.Vertical
                    )
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Hello there \uD83D\uDC4B",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                        Text(
                            text = "Please enter your username and password to sign in",
                            modifier = Modifier.padding(vertical = 10.dp),
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        InputField(
                            label = "Username", type = KeyboardType.Text,
                            value = inputViewModel.username,
                            onValueChange = { inputViewModel.updateUsername(it) },
                            placeHolderText = "Enter your username",
                            isError = inputViewModel.usernameError,
                            errorText = if (inputViewModel.username.trim()
                                    .isEmpty()
                            ) "Username can't be empty" else "This username doesn't exist in our database"
                        )

                        InputField(
                            label = "Password", type = KeyboardType.Password,
                            value = inputViewModel.password,
                            onValueChange = { inputViewModel.updatePassword(it) },
                            placeHolderText = "Enter your password",
                            isError = inputViewModel.passwordError,
                            trailingIcon = {
                                IconButton(
                                    onClick = { inputViewModel.togglePasswordVisible() }
                                ) {
                                    Icon(
                                        imageVector = if (inputViewModel.passwordVisible)
                                            Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = null,
                                        tint = AppConstants.DarkYellow
                                    )
                                }
                            },
                            visualTransformation = if (inputViewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            errorText = if(inputViewModel.password.trim().isEmpty()) "Password can't be empty" else "Incorrect Password"
                        )
                        //TODO: update errorText of both fields
                    }
                }
                Column {
                    Divider(thickness = 1.dp, color = Color.LightGray)
                    CustomButton(
                        modifier = Modifier.padding(vertical = 20.dp),
                        darkBackground = true,
                        text = "Sign In",
                        shadow = true
                    ) {
                        inputViewModel.submitRegForm()
                    }
                }
            }
        }
    }
}