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

@Composable
fun MeuApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavigationComponent(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("home") },
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("list/{evento}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("evento")?.toIntOrNull()
            categoryId?.let {
                ListScreen(navController = navController, evento = it)
            }
        }
        composable("schedule/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
            categoryId?.let {
                ScheduleScreen(it)
            }
        }
    }
}