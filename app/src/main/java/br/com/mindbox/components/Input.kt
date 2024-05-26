package br.com.mindbox.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun Input(
    value: String,
    placeholder: String,
    label: String,
    modifier: Modifier,
    keyboardType: KeyboardType,
    stateValue: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {stateValue(it)},
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        placeholder = {
            Text(
                text = placeholder,
                color =  Color(0xFFEFB8C8)
            )
        },
        label = {
            Text(
                text = label,
                color =  Color(0xFFEFB8C8)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}