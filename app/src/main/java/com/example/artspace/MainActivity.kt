package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    //Variable con el número de cuadro
    var numCuadro by remember { mutableStateOf(1) }
    var textoCuadro by remember { mutableStateOf("1") }

    //Valor con el número total de cuadros
    val total_cuadros = 3

    //Asignamos el valor de textoCuadro a la variable numCuadro
    val valorTextoCuadro = textoCuadro.toIntOrNull()
    if ( valorTextoCuadro != null &&
        valorTextoCuadro >= 1 &&
        valorTextoCuadro <= total_cuadros) {
            numCuadro = valorTextoCuadro
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val modifierCuadro = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.LightGray)

        when (numCuadro) {
            1 -> {
                DetallesCuadro(
                    imagen = R.drawable.girasoles_van_gogh,
                    stringResource(R.string.los_girasoles_nombre),
                    stringResource(R.string.los_girasoles_autor),
                    stringResource(R.string.los_girasoles_anyo),
                    modifier = modifierCuadro
                )
            }
            2 -> {
                DetallesCuadro(
                    imagen = R.drawable.el_grito_edvard_munch,
                    stringResource(R.string.el_grito_nombre),
                    stringResource(R.string.el_grito_autor),
                    stringResource(R.string.el_grito_anyo),
                    modifier = modifierCuadro
                )
            }
            3 -> {
                DetallesCuadro(
                    imagen = R.drawable.paseo_mar_sorolla,
                    stringResource(R.string.paseo_mar_nombre),
                    stringResource(R.string.paseo_mar_autor),
                    stringResource(R.string.paseo_mar_anyo),
                    modifier = modifierCuadro
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row() {
            TextField(
                value = textoCuadro,
                onValueChange = { textoCuadro = it},
                singleLine = true,
                label = { Text(stringResource(R.string.num_cuadro)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
        ) {
            Button(
                onClick = {
                    numCuadro = if (numCuadro>1) numCuadro-1 else total_cuadros
                    textoCuadro = numCuadro.toString()
                }
            ) {
                Text(
                    text = stringResource(R.string.boton_anterior),
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Button(
                onClick = {
                    numCuadro = if (numCuadro<total_cuadros) numCuadro+1 else 1
                    textoCuadro = numCuadro.toString()
                }
            ) {
                Text(
                    text = stringResource(R.string.boton_siguiente),
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun DetallesCuadro(@DrawableRes imagen: Int,
           nombre: String,
           autor: String,
           anyo: String,
           modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = imagen),
            contentDescription = nombre,
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = nombre,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            //text = "Autor: $autor",
            text = stringResource(R.string.etiqueta_autor, autor),
            fontSize = 22.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            //text = "Año: $anyo",
            text = stringResource(R.string.etiqueta_anyo, anyo),
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}