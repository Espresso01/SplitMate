package ru.fefu.splitmate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillInputForm(
    totalAmount: String,
    peopleCount: String,
    tipPercentage: String,
    isCalculateEnabled: Boolean,
    onTotalAmountChange: (String) -> Unit,
    onPeopleCountChange: (String) -> Unit,
    onTipPercentageChange: (String) -> Unit,
    onCalculateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Введите сумму счета",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp),
            color = Color.Red
        )

        OutlinedTextField(
            value = totalAmount,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toDoubleOrNull() != null) {
                    onTotalAmountChange(newValue)
                }
            },
            label = { Text("Сумма счета", color = Color.Red) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = peopleCount,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                    onPeopleCountChange(newValue)
                }
            },
            label = { Text("Количество человек", color = Color.Red) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tipPercentage,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toDoubleOrNull() != null) {
                    onTipPercentageChange(newValue)
                }
            },
            label = { Text("Процент чая", color = Color.Red) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onCalculateClick,
            enabled = isCalculateEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
                disabledContainerColor = Color.Red.copy(alpha = 0.5f),
                disabledContentColor = Color.White.copy(alpha = 0.7f)
            )
        ) {
            Text("ПОСЧИТАЕМ", style = MaterialTheme.typography.titleMedium)
        }
    }
}