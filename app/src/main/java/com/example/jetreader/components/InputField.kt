package com.example.jetreader.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "Username",
    hint: String = "Username",
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textColor: Color = Color.Black,
    type: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeHolderText: String = "Enter your username",
    errorText: String = "Error"
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = label,
            modifier = Modifier.padding(vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            color = textColor,
            fontSize = 15.sp
        )
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            isError = isError,
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            keyboardActions = onAction,
            maxLines = maxLines,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                textColor = textColor,
                cursorColor = AppConstants.DarkYellow,
                focusedBorderColor = AppConstants.DarkYellow,
                unfocusedBorderColor = Color.Gray
            ),
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            textStyle = TextStyle(fontWeight = FontWeight.Bold),
            placeholder = {
                Text(text = placeHolderText, color = Color.LightGray, fontSize = 15.sp)
            },
            supportingText = {
                if (isError)
                    Text(text = errorText,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp)
                else null
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}