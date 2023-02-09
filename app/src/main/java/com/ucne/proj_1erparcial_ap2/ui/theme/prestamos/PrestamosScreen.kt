package com.ucne.proj_1erparcial_ap2.ui.theme.prestamos


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity

@Composable
fun PrestamosScreen(viewModel: PrestamosViewModel = hiltViewModel()) {

    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

        Text(
            text = "Registro de Prestamos", fontSize = 32.sp, // Titulo inicial
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Spacer(modifier = Modifier.padding(6.dp))

        PrestamosBody(viewModel)

        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = "Lista de Prestamos", fontSize = 32.sp, // Consulta de prestamos
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        Spacer(modifier = Modifier.padding(8.dp))

        val uiState by viewModel.uiState.collectAsState()
        PrestamosListaScreen(uiState.prestamosList)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PrestamosBody(
    viewModel: PrestamosViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField( // Campo deudor
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.deudor,
            onValueChange = { viewModel.deudor = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Deudor") }
        )

        OutlinedTextField( // Campo concepto
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Article,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Concepto") }
        )

        OutlinedTextField( // Campo monto
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.RequestPage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Monto") }
        )

        Spacer(modifier = Modifier.padding(6.dp))

        ExtendedFloatingActionButton( // Boton guardar

            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentColor = Color(0xFF272727),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Guardar") },
            onClick = { viewModel.insertar() }
        )
    }
}

@Composable
private fun PrestamosListaScreen(prestamosList: List<PrestamosEntity>) {
    LazyColumn {
        items(prestamosList) { prestamos ->
            PrestamosFila(prestamos)
        }
    }
}


@Composable
private fun PrestamosFila(prestamos: PrestamosEntity) { // Lista de los prestamos

    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = prestamos.deudor,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {

            Text(
                text = prestamos.concepto,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(3f)
            )
            Text(
                text = "$ ", color = Color(0xFF6E9C64),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                textAlign = TextAlign.End,
            )

            Text(
                String.format("%.2f", prestamos.monto),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}
