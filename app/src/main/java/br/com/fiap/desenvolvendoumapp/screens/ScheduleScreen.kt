package br.com.fiap.desenvolvendoumapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.desenvolvendoumapp.R

@Composable
fun ScheduleScreen(
    categoryId: Int
) {
    // Dados estáticos para o evento de Futsal
    val eventName = "Futsal"
    val eventDate = "18/12/2024"
    val eventLocation = "Rua Padre Jeova Jire, Santana - SP"
    val eventClassification = "Acima de 16 anos"
    val eventCategory = "Esporte 1"


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFEFEFEF))
    ) {
        // Header
        Text(
            text = "Agendamento",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Evento Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.futsal),
                        contentDescription = eventName,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = eventName, fontSize = 20.sp)
                        Text(text = eventLocation)
                        Text(text = "Data: $eventDate")
                    }
                }

                Text(text = "CLASSIFICAÇÃO: $eventClassification")
                Text(text = "CATEGORIA: $eventCategory")
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Prepare-se para um emocionante evento de $eventName que acontecerá no dia $eventDate! Localizado na $eventLocation, o evento promete uma experiência única para todos os amantes do esporte. Não perca essa chance de vivenciar o espírito do $eventName de perto!",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F779E))
        ) {
            Text(text = "Confirmar")
        }
    }
}
