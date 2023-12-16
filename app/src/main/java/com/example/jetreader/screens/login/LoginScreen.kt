package com.example.jetreader.screens.login

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetreader.components.CustomButton
import com.example.jetreader.components.CustomTopBar
import com.example.jetreader.components.InputField
import com.example.jetreader.components.InputViewModel
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.navigation.ReaderScreens
import com.example.jetreader.utils.AppConstants

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    inputViewModel: InputViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        topBar = { CustomTopBar("", navController) }
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
                            label = "Email", type = KeyboardType.Email,
                            value = inputViewModel.email,
                            onValueChange = {
                                inputViewModel.updateEmail(it)
                            },
                            placeHolderText = "Enter your email ID",
                            isError = inputViewModel.emailError,
                            enabled = inputViewModel.inputFieldEnabled,
                            errorText = if (inputViewModel.email.trim()
                                    .isEmpty()
                            ) "Email ID can't be empty" else "Invalid email ID"
                        )

                        InputField(
                            label = "Password", type = KeyboardType.Password,
                            value = inputViewModel.password,
                            onValueChange = { inputViewModel.updatePassword(it) },
                            placeHolderText = "Enter your password",
                            isError = inputViewModel.passwordError,
                            enabled = inputViewModel.inputFieldEnabled,
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
                            errorText = if (inputViewModel.password.trim()
                                    .isEmpty()
                            ) "Password can't be empty" else "Password must be at least 6 characters long"
                        )
                        //TODO: update errorText of both fields
                    }
                }
                Column {
                    Divider(thickness = 1.dp, color = Color.LightGray)
                    CustomButton(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
                        backgroundColor = AppConstants.DarkYellow,
                        textColor = Color.White,
                        text = "Sign In",
                        enabled = inputViewModel.inputFieldEnabled,
                        shadow = true
                    ) {
                        if (inputViewModel.checkUserDetails("login")) {
                            inputViewModel.disableInputField()
                            loginViewModel.signInWithEmailAndPassword(
                                inputViewModel.email,
                                inputViewModel.password,
                                retry = {
                                    inputViewModel.enableInputField()
                                    Toast.makeText(
                                        context,
                                        "Couldn't fetch details. Kindly retry",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            ) {
                                navController.navigate(ReaderScreens.HomeScreen.name) {
                                    popUpTo(ReaderScreens.OnBoardScreen.name) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (loginViewModel.loading)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressBar()
                }
            BackHandler(
                enabled = !inputViewModel.inputFieldEnabled
            ) { // Don't let user go back
            }
        }
    }
}