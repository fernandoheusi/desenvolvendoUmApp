package br.com.fiap.desenvolvendoumapp.screens

import android.util.Log
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
import br.com.fiap.desenvolvendoumapp.model.Category
import br.com.fiap.desenvolvendoumapp.service.RetrofitFactory
import br.com.fiap.desenvolvendoumapp.ui.theme.Roboto
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {


    var categoryList by remember {
        mutableStateOf(listOf<Category>())
    }

    var searchText by remember { mutableStateOf("") }
    var callCategories = RetrofitFactory().getCategoriesService().getCategories()
    callCategories.enqueue(object : Callback<List<Category>>{
        override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
            categoryList = response.body()!!
        }

        override fun onFailure(call: Call<List<Category>>, t: Throwable) {
            Log.i("Fiap","on failure: ${t.message}")
        }
    })

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
        colors = ButtonDefaults.buttonColors(containerColor = Color(category.color.toLong(radix = 16))),
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
