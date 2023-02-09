package com.ucne.proj_1erparcial_ap2.ui.theme.prestamos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity

@Composable
fun PrestamosScreen(viewModel: PrestamosViewModel = hiltViewModel()) {

    Column(Modifier.fillMaxSize()) {
        PrestamosBody(viewModel)

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

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.deudor,
            onValueChange = {  viewModel.deudor = it },
            label = { Text("Deudor") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.concepto,
            onValueChange = {  viewModel.concepto = it },
            label = { Text("Concepto") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            label = { Text("Monto") }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.TwoTone.Save , contentDescription = "Guardar") },
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
private fun PrestamosFila(prestamos: PrestamosEntity) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = prestamos.deudor,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(3f)
            )

            Text(
                text = prestamos.concepto,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(3f)
            )
            Text(
                String.format("%.2f", prestamos.monto),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
                Divider(Modifier.fillMaxWidth())
    }
