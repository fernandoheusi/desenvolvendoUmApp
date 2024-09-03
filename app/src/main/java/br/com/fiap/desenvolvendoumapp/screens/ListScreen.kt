package br.com.fiap.desenvolvendoumapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.desenvolvendoumapp.R
import br.com.fiap.desenvolvendoumapp.model.Category
import br.com.fiap.desenvolvendoumapp.model.Event
import br.com.fiap.desenvolvendoumapp.service.RetrofitFactory
import br.com.fiap.desenvolvendoumapp.ui.theme.Roboto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListScreen(modifier: Modifier = Modifier, navController: NavController) {
    var eventList = listOf<Event>()
    var searchText by remember { mutableStateOf("") }


    var callEvents = RetrofitFactory().getEventsService().getEvents()
    callEvents.enqueue(object : Callback<List<Event>> {
        override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
            eventList = response.body()!!
        }

        override fun onFailure(call: Call<List<Event>>, t: Throwable) {
            Log.i("Fiap","on failure: ${t.message}")
        }
    })

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Text(
            text = "Futsal",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            fontFamily = Roboto,
            color = Color(0xFF1F779E)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Busque por eventos") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.magnifying_glass),
                    contentDescription = "Icone de lupa"
                )
            },
            shape = RoundedCornerShape(percent = 50)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Eventos dispon'iveis",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            fontFamily = Roboto,
            color = Color(0xFF1F779E)
        )


        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF15BA35)),
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Futsal na quadra",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontFamily = Roboto,
                    color = Color.White
                )
                Text(
                    text = "Quarta - 17/08 as 09:30",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontFamily = Roboto,
                    color = Color.White
                )
            }

        }
    }
}
