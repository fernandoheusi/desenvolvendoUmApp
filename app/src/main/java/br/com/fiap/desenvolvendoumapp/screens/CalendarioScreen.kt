package br.com.fiap.desenvolvendoumapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarioScreen(modifier: Modifier = Modifier) {
    var dataSelecionada by remember { mutableStateOf<LocalDate?>(null) }
    var mesAtual by remember { mutableStateOf(YearMonth.now()) }
    val diasDoMes = generateDaysForMonth(mesAtual)

    Scaffold(
        topBar = { HeaderSection() },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { mesAtual = mesAtual.minusMonths(1) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Mês Anterior"
                    )
                }
                Text(
                    text = "${mesAtual.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${mesAtual.year}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

                IconButton(onClick = { mesAtual = mesAtual.plusMonths(1) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Próximo Mês"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            diasDoMes.forEach { semana ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    semana.forEach { dia ->
                        val selecionado = dia == dataSelecionada
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = if (selecionado) MaterialTheme.colorScheme.primary else Color.Transparent
                                )
                                .clickable {
                                    dataSelecionada = dia
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = dia.dayOfMonth.toString(),
                                color = if (selecionado) MaterialTheme.colorScheme.onPrimary else Color.DarkGray,
                                fontWeight = if (selecionado) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Ação para seleção de horário */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = if (dataSelecionada != null)
                        "DIA ${dataSelecionada!!.dayOfMonth}/${dataSelecionada!!.monthValue} as 7:00 PM"
                    else
                        "Selecione uma data",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Ação de confirmação da data */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Confirmar", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = "JUDO",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Rua Padre Jeova Jire, Santana - SP.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "CLASSIFICAÇÃO: ACIMA DE 16 ANOS",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )
    }
}
fun generateDaysForMonth(month: YearMonth): List<List<LocalDate>> {
    val firstDayOfMonth = month.atDay(1)
    val daysInMonth = month.lengthOfMonth()
    val days = (1..daysInMonth).map { day ->
        firstDayOfMonth.plusDays((day - 1).toLong())
    }

    val weeks = mutableListOf<List<LocalDate>>()
    var currentWeek = mutableListOf<LocalDate>()

    days.forEach { day ->
        if (currentWeek.isEmpty() || day.dayOfWeek != java.time.DayOfWeek.MONDAY) {
            currentWeek.add(day)
        } else {
            weeks.add(currentWeek)
            currentWeek = mutableListOf(day)
        }
    }
    if (currentWeek.isNotEmpty()) {
        weeks.add(currentWeek)
    }

    return weeks
}
