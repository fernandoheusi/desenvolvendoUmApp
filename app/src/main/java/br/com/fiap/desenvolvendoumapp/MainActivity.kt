package br.com.fiap.desenvolvendoumapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.desenvolvendoumapp.ui.theme.DesenvolvendoUmAppTheme
import br.com.fiap.desenvolvendoumapp.ui.theme.Roboto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesenvolvendoUmAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
//                    HomeScreen()
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var text by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ){
        TextField(
            value = text,
            onValueChange = { letter ->
                text = letter
            },
            modifier = Modifier.fillMaxWidth(),
//            label= {
//                Text(text = "Busque por eventos")
//            },
            placeholder = {
                Text(text = "Busque por eventos")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.magnifying_glass),
                    contentDescription = "Icone de lupa"
                )
            }
        )
        Text(text = "Futsal")
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF15BA35)),
        ) {

            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ){
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

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    var textEmail by remember {
        mutableStateOf("")
    }

    var textSenha by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        TextField(
            value = textEmail,
            onValueChange = { letter ->
                textEmail = letter
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "e-mail")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Verificado"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(color = Color(0xFF1F779E), fontSize = 16.sp)
        )
        TextField(
            value = textSenha,
            onValueChange = { letter ->
                textSenha = letter
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "senha")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.eyecrossed),
                    contentDescription = "Olho riscado"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(color = Color(0xFF1F779E), fontSize = 16.sp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    LoginScreen()
}