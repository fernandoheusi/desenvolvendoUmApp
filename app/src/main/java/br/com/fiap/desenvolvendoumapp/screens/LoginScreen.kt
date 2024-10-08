package br.com.fiap.desenvolvendoumapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.desenvolvendoumapp.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
) {
    var textEmail by remember { mutableStateOf("") }
    var textSenha by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                if (textEmail == "admin@admin.com" && textSenha == "admin") {
                    onLoginClick()
                } else {
                    errorMessage = "E-mail ou senha incorretos!"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F779E))
        ) {
            Text(text = "Entrar")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Esqueceu a Senha?", color = Color(0xFF1F779E))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ou")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFF1F779E))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(30.dp)
                )
            }

            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFF1F779E))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Não Possui uma conta?", color = Color(0xFF1F779E))
        Text(
            text = "Clique Aqui!",
            color = Color(0xFF1F779E),
            fontWeight = FontWeight.Bold,
        )
    }
}
