package com.empresa.snacks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.snacks.ui.theme.SnacksAppTheme

private val ColorFondoAplicacion = Color(0xFFFFFBF3)
private val ColorVerdePrincipal = Color(0xFF1F5E1F)
private val ColorVerdeOscuro = Color(0xFF0B2E13)
private val ColorAmarilloMaiz = Color(0xFFFFC928)
private val ColorTarjetaMenu = Color(0xFFFFF4D8)
private val ColorBotonDeshabilitado = Color(0xFFE3DDD0)
private val ColorTextoDeshabilitado = Color(0xFF8C867B)

class MainActivity : ComponentActivity() {

    // onCreate se ejecuta cuando Android crea esta pantalla principal.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que la interfaz use mejor el espacio de la pantalla.
        enableEdgeToEdge()

        // setContent indica que la interfaz se construirá con Jetpack Compose.
        setContent {
            SnacksAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = ColorFondoAplicacion
                ) { espacioSeguroPantalla ->

                    PantallaMenuPrincipal(
                        modificador = Modifier.padding(espacioSeguroPantalla)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaMenuPrincipal(
    modificador: Modifier = Modifier
) {
    Column(
        modifier = modificador
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoPrincipal()

        Text(
            text = "Sistema de ventas para elotes y snacks",
            style = MaterialTheme.typography.bodyLarge,
            color = ColorVerdePrincipal
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = ColorTarjetaMenu
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Menú principal",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = ColorVerdeOscuro
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Aquí después abriremos la pantalla para calcular una compra.
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorAmarilloMaiz,
                        contentColor = ColorVerdeOscuro
                    )
                ) {
                    Text(
                        text = "Calcular compra",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        // Aquí después abriremos la pantalla de ventas registradas.
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    enabled = false,
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = ColorBotonDeshabilitado,
                        disabledContentColor = ColorTextoDeshabilitado
                    )
                ) {
                    Text(text = "Ventas del día")
                }
            }
        }
    }
}

@Composable
fun LogoPrincipal() {
    Image(
        painter = painterResource(id = R.drawable.logo_los_de_aca),
        contentDescription = "Logo de Los de Acá",
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
fun VistaPreviaMenuPrincipal() {
    SnacksAppTheme {
        PantallaMenuPrincipal()
    }
}