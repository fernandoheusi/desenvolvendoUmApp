package br.com.fiap.desenvolvendoumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.desenvolvendoumapp.screens.HomeScreen
import br.com.fiap.desenvolvendoumapp.screens.ListScreen
import br.com.fiap.desenvolvendoumapp.screens.LoginScreen
import br.com.fiap.desenvolvendoumapp.screens.ScheduleScreen
import br.com.fiap.desenvolvendoumapp.ui.theme.DesenvolvendoUmAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesenvolvendoUmAppTheme {
                MeuApp()
            }
        }
    }
}


// nova "function main" do app
@Composable
fun MeuApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavigationComponent(navController, Modifier.padding(innerPadding))
    }
}

/* funcao de navegacao
* startDestination = login --> onde vai começar o app
* lista de composable (tela)
* */
@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "login", modifier = modifier) {
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("home") },
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("list") {
            ListScreen(navController = navController)

        }
        composable("schedule/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            categoryId?.let {
                ScheduleScreen(it)
            }
        }
    }
}