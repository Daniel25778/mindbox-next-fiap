package br.com.mindbox.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.mindbox.R

@Composable
fun Input(
    value: String?,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: ((String) -> Unit)?
) {
    if (value != null) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                onValueChange?.invoke(newValue)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    color = colorResource(id = R.color.grey_550)
                )
            },
            label = {
                Text(
                    text = label,
                    color = colorResource(id = R.color.white)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colorResource(id = R.color.white),
                unfocusedTextColor = colorResource(id = R.color.white),
                cursorColor = colorResource(id = R.color.white),
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.grey_550),
                focusedLabelColor = colorResource(id = R.color.grey_550),
                unfocusedLabelColor = colorResource(id = R.color.grey_550),
                disabledPlaceholderColor = colorResource(id = R.color.white)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}
