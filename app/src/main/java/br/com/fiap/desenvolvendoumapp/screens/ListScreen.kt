package br.com.fiap.desenvolvendoumapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.desenvolvendoumapp.R
import br.com.fiap.desenvolvendoumapp.model.Event
import br.com.fiap.desenvolvendoumapp.service.RetrofitFactory
import br.com.fiap.desenvolvendoumapp.ui.theme.Roboto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListScreen(modifier: Modifier = Modifier, navController: NavController, evento: Int) {
    var eventList by remember {
        mutableStateOf(listOf<Event>())
    }
    var searchText by remember { mutableStateOf("") }

    var callEvents:Call<List<Event>> = RetrofitFactory().getEventsService().getEvents()
    when(evento){
        0 -> callEvents = RetrofitFactory().getEventsService().getEventsFutsal()
        1 -> callEvents = RetrofitFactory().getEventsService().getEventsCorrida()
        2 -> callEvents = RetrofitFactory().getEventsService().getEventsVolei()
        3 -> callEvents = RetrofitFactory().getEventsService().getEventsBasket()
        4 -> callEvents = RetrofitFactory().getEventsService().getEventsFit()
        5 -> callEvents = RetrofitFactory().getEventsService().getEventsMarciais()
        6 -> callEvents = RetrofitFactory().getEventsService().getEventsYoga()
        7 -> callEvents = RetrofitFactory().getEventsService().getEventsNatacao()
        8 -> callEvents = RetrofitFactory().getEventsService().getEventsGinastica()
        9 -> callEvents = RetrofitFactory().getEventsService().getEventsHiphop()
    }

    callEvents.enqueue(object : Callback<List<Event>> {
        override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
            eventList = response.body()!!
            Log.i("Fiap","on failure: ${response.body()}")
        }

        override fun onFailure(call: Call<List<Event>>, t: Throwable) {
            Log.i("Fiap", "on failure: ${t.message}")
        }
    })

    Column(
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
            text = "Eventos disponíveis",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            fontFamily = Roboto,
            color = Color(0xFF1F779E)
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(){
           items(eventList){ event ->
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
                           text = event.name,
                           fontSize = 11.sp,
                           fontWeight = FontWeight.Bold,
                           textAlign = TextAlign.Start,
                           fontFamily = Roboto,
                           color = Color.White
                       )
                       Text(
                           text = event.date,
                           fontSize = 11.sp,
                           fontWeight = FontWeight.Bold,
                           textAlign = TextAlign.Start,
                           fontFamily = Roboto,
                           color = Color.White
                       )
                   }

               }

               Spacer(modifier = Modifier.height(15.dp))
           }

        }
    }
}

// Compose Preview
@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen(navController = NavController(LocalContext.current))
}