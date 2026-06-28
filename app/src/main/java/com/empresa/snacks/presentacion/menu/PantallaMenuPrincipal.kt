package com.empresa.snacks.presentacion.menu

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.snacks.R
import com.empresa.snacks.comun.estilo.ColorAmarilloMaiz
import com.empresa.snacks.comun.estilo.ColorBotonDeshabilitado
import com.empresa.snacks.comun.estilo.ColorFondoAplicacion
import com.empresa.snacks.comun.estilo.ColorTarjetaMenu
import com.empresa.snacks.comun.estilo.ColorTextoDeshabilitado
import com.empresa.snacks.comun.estilo.ColorVerdeOscuro
import com.empresa.snacks.comun.estilo.ColorVerdePrincipal
import com.empresa.snacks.ui.theme.SnacksAppTheme

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

        Spacer(modifier = Modifier.height(12.dp))

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
                        // Después abriremos la pantalla para calcular una compra.
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
                        // Después abriremos la pantalla de ventas registradas.
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
private fun LogoPrincipal() {
    Image(
        painter = painterResource(id = R.drawable.logo_los_de_aca),
        contentDescription = "Logo de Los de Acá",
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF3)
@Composable
fun VistaPreviaMenuPrincipal() {
    SnacksAppTheme {
        PantallaMenuPrincipal()
    }
}