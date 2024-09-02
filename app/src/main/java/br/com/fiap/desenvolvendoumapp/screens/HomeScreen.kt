package br.com.fiap.desenvolvendoumapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.desenvolvendoumapp.R
import br.com.fiap.desenvolvendoumapp.ui.theme.Roboto
import coil.compose.AsyncImage

data class Category(
    val id: Int,
    val name: String,
    val color: Color,
    val pictureURL: String,
)

val categoryList = listOf(
    createCategory(0, "Futsal", Color(0xFF091F07), "futsal.png"),
    createCategory(1, "Corrida", Color(0xFFA32822), "corrida.png"),
    createCategory(2, "Yoga", Color(0xFFFF95D5), "yoga.png"),
    createCategory(3, "Fit Dance", Color(0xFFFF7CAF), "fit.png"),
    createCategory(4, "Natação", Color(0xFF45757C), "natacao.png"),
    createCategory(5, "Volei", Color(0xFFFFD542), "volei.png"),
    createCategory(6, "Basketball", Color(0xFFC36725), "basket.png"),
    createCategory(7, "Ginástica", Color(0xFF1F1F1F), "ginastica.png"),
    createCategory(8, "Artes Marciais", Color(0xFF770505), "marciais.png"),
    createCategory(9, "Hip Hop", Color(0xFF767676), "hiphop.png"),
)

fun createCategory(id: Int, name: String, color: Color, imageName: String): Category {
    val baseUrl = "https://raw.githubusercontent.com/fernandoheusi/desenvolvendoUmApp/staging/app/src/main/res/drawable/"
    return Category(
        id = id,
        name = name,
        color = color,
        pictureURL = "$baseUrl$imageName"
    )
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        GreetingHeader(name = "Cecília")
        Spacer(modifier = Modifier.height(10.dp))
        SearchBar(
            text = searchText,
            onTextChange = { searchText = it }
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

        CategoryGrid(categories = categoryList)
    }
}

@Composable
fun GreetingHeader(name: String) {
    Text(
        text = "Olá, $name!",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        fontFamily = Roboto,
        color = Color(0xFF1F779E)
    )
}

@Composable
fun SearchBar(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
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
}

@Composable
fun CategoryGrid(categories: List<Category>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { category ->
            CategoryButton(category = category)
        }
    }
}

@Composable
fun CategoryButton(category: Category) {
    Button(
        onClick = { /* TODO */ },
        modifier = Modifier
            .width(165.dp)
            .height(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = category.color),
        shape = RoundedCornerShape(percent = 10)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxSize()
        ) {
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
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}
