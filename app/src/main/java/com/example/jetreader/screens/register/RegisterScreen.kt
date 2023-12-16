package com.example.jetreader.screens.register

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetreader.components.CustomButton
import com.example.jetreader.components.CustomTopBar
import com.example.jetreader.components.InputField
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.navigation.ReaderScreens
import com.example.jetreader.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
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
                            text = "Create an Account \uD83D\uDD0F",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        InputField(
                            label = "Username", type = KeyboardType.Text,
                            value = registerViewModel.username,
                            onValueChange = { registerViewModel.updateUsername(it) },
                            placeHolderText = "Enter your username",
                            isError = registerViewModel.usernameError,
                            errorText = "Username should be at least 6 chars long"
                        )
                        InputField(
                            label = "Email", type = KeyboardType.Email,
                            value = registerViewModel.email,
                            onValueChange = { registerViewModel.updateEmail(it) },
                            placeHolderText = "Enter your Email ID",
                            isError = registerViewModel.emailError,
                            errorText = "Enter a valid email ID"
                        )
                        InputField(
                            label = "Password", type = KeyboardType.Password,
                            value = registerViewModel.password,
                            onValueChange = { registerViewModel.updatePassword(it) },
                            placeHolderText = "Enter your password",
                            isError = registerViewModel.passwordError,
                            trailingIcon = {
                                IconButton(
                                    onClick = { registerViewModel.togglePasswordVisible() }
                                ) {
                                    Icon(
                                        imageVector = if (registerViewModel.passwordVisible)
                                            Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = null,
                                        tint = AppConstants.DarkYellow
                                    )
                                }
                            },
                            visualTransformation = if (registerViewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            errorText = "Password must be at least 6 chars long"
                        )
                        InputField(
                            label = "Confirm Password", type = KeyboardType.Password,
                            value = registerViewModel.confirmPassword,
                            onValueChange = { registerViewModel.updateConfirmPassword(it) },
                            imeAction = ImeAction.Done,
                            placeHolderText = "Confirm your password",
                            isError = registerViewModel.confirmPasswordError,
                            trailingIcon = {
                                IconButton(
                                    onClick = { registerViewModel.togglePasswordVisible() }
                                ) {
                                    Icon(
                                        imageVector = if (registerViewModel.passwordVisible)
                                            Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = null,
                                        tint = AppConstants.DarkYellow
                                    )
                                }
                            },
                            visualTransformation = if (registerViewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            errorText = "Password doesn't match"
                        )
                    }
                }
                Column {
                    Divider(thickness = 1.dp, color = Color.LightGray)
                    CustomButton(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
                        backgroundColor = AppConstants.DarkYellow, textColor = Color.White,
                        text = "Sign Up",
                        shadow = true
                    ) {
                        if (registerViewModel.checkUserDetails("register")) {
                            registerViewModel.disableInputField()
                            registerViewModel.createUserWithEmailAndPassword(
                                email = registerViewModel.email,
                                password = registerViewModel.password,
                                username = registerViewModel.username,
                                retry = {
                                    registerViewModel.enableInputField()
                                    Toast.makeText(
                                        context,
                                        "Something went wrong! Please try again..",
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
            if (registerViewModel.loading)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressBar()
                }
            BackHandler(
                enabled = !registerViewModel.inputFieldEnabled
            ) { // Don't let user go back
            }
        }
    }
}