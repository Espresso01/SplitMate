package ru.fefu.splitmate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.fefu.splitmate.model.SplitCalculation

@Composable
fun SplitResultCard(
    calculation: SplitCalculation?,
    onNewCalculationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        calculation?.let { calc ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = androidx.compose.foundation.BorderStroke(
                    width = 2.dp,
                    color = Color.Red
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Результат расчета",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = Color.Red
                    )

                    CalculationRow(
                        label = "Сумма счета:",
                        value = String.format("%.2f", calc.totalAmount)
                    )

                    CalculationRow(
                        label = "Чай (${calc.tipPercentage}%):",
                        value = String.format("%.2f", calc.tipAmount)
                    )

                    CalculationRow(
                        label = "Итог с чаем:",
                        value = String.format("%.2f", calc.totalWithTip),
                        isHighlighted = true,
                        highlightColor = Color.Red
                    )

                    CalculationRow(
                        label = "С каждого:",
                        value = String.format("%.2f", calc.perPerson),
                        isHighlighted = true,
                        highlightColor = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNewCalculationClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 3.dp
                )
            ) {
                Text("НОВЫЙ СТОЛ - НОВЫЙ ЧАЙ", style = MaterialTheme.typography.titleMedium)
            }
        } ?: run {
            Text(
                text = "Расчет не найден",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp),
                color = Color.Red
            )

            Button(
                onClick = onNewCalculationClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text("НОВЫЙ СТОЛ - НОВЫЙ ЧАЙ", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
private fun CalculationRow(
    label: String,
    value: String,
    isHighlighted: Boolean = false,
    highlightColor: Color = Color.Red
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (isHighlighted) MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ) else MaterialTheme.typography.titleMedium,
            color = if (isHighlighted) highlightColor else Color.DarkGray
        )
        Text(
            text = "$value ₽",
            style = if (isHighlighted) MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ) else MaterialTheme.typography.titleMedium,
            color = if (isHighlighted) highlightColor else Color.DarkGray
        )
    }
}