package br.com.fiap.desenvolvendoumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.desenvolvendoumapp.ui.theme.DesenvolvendoUmAppTheme
import br.com.fiap.desenvolvendoumapp.ui.theme.Roboto
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesenvolvendoUmAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    data class Category(
        val id: Int,
        val name: String,
        val color: Color,
        val pictureURL: String,
    )
    var categoryList = listOf(
        Category(
            id=0,
            name="Futsal",
            color=Color(0xFF091F07),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/futsal.png",
        ),
        Category(
            id=1,
            name="Corrida",
            color=Color(0xFFA32822),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/corrida.png",
        ),
        Category(
            id=2,
            name="Yoga",
            color=Color(0xFFFF95D5),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/yoga.png",
        ),
        Category(
            id=3,
            name="Fit Dance",
            color=Color(0xFFFF7CAF),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/fit.png",
        ),
        Category(
            id=4,
            name="Natação",
            color=Color(0xFF45757C),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/natacao.png",
        ),
        Category(
            id=5,
            name="Volei",
            color=Color(0xFFFFD542),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/volei.png",
        ),
        Category(
            id=6,
            name="Basketball",
            color=Color(0xFFC36725),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/basket.png",
        ),
        Category(
            id=7,
            name="Ginástica",
            color=Color(0xFF1F1F1F),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/ginastica.png",
        ),
        Category(
            id=8,
            name="Artes Marciais",
            color=Color(0xFF770505),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/marciais.png",
        ),
        Category(
            id=9,
            name="Hip Hop",
            color=Color(0xFF767676),
            pictureURL = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/hiphop.png",
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = "Olá, Cecília!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontFamily = Roboto,
                color = Color(0xFF1F779E)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { letter -> text = letter },
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
            text = "Categorias",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            fontFamily = Roboto,
            color = Color(0xFF1F779E)
        )
        Spacer(modifier = Modifier.height(15.dp))


        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(categoryList){ category ->
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(165.dp)
                        .height(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = category.color),
                    shape = RoundedCornerShape(percent = 10)
                ) {


                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize(),
                    ){
                        Text(
                            text = category.name,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            fontFamily = Roboto,
                            color = Color.White,
                            modifier = Modifier.width(65.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        AsyncImage(
                            model = category.pictureURL,
                            contentDescription = category.name,
                            modifier = Modifier.fillMaxHeight().fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x0015BA35),
                    contentColor = Color(0xFF1F779E)
                ),
                modifier = Modifier
                    .width(60.dp)
                    .absoluteOffset(x = -20.dp)
                ) {
                Icon(painter = painterResource(id = R.drawable.arrowback) , contentDescription = "voltar")
            }
            Text(
                text = "Futsal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontFamily = Roboto,
                color = Color(0xFF1F779E)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { letter -> text = letter },
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
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF15BA35)),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ){

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
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

                Image(
                    painter = painterResource(id = R.drawable.soccer),
                    contentDescription = "Futsal",
                )
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var textEmail by remember { mutableStateOf("") }
    var textSenha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de email
        OutlinedTextField(
            value = textEmail,
            onValueChange = { textEmail = it },
            label = { Text("Email") },
            placeholder = { Text("Digite seu E-mail") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Verificado"
                )
            },
            textStyle = TextStyle(color = Color(0xFF1F779E), fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de senha
        OutlinedTextField(
            value = textSenha,
            onValueChange = { textSenha = it },
            label = { Text("Senha") },
            placeholder = { Text("Senha") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.eyecrossed),
                    contentDescription = "Olho riscado"
                )
            },
            textStyle = TextStyle(color = Color(0xFF1F779E), fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F779E))
        ) {
            Text(text = "Entrar")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    DesenvolvendoUmAppTheme {
        HomeScreen()
    }
}
