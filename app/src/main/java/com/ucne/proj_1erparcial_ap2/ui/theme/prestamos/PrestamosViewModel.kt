package com.ucne.proj_1erparcial_ap2.ui.theme.prestamos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PrestamosUiState(
    val prestamosList: List<PrestamosEntity> = emptyList()
)

@HiltViewModel
class PrestamosViewModel @Inject constructor(
    private val prestamosRepository: PrestamosRepository
) : ViewModel() {

    var deudor by mutableStateOf("")
    var concepto by mutableStateOf("")
    var monto by mutableStateOf("")

    var uiState = MutableStateFlow(PrestamosUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            prestamosRepository.getList().collect{lista ->
                uiState.update {
                    it.copy(prestamosList = lista)
                }
            }
        }
    }

    fun insertar() {
        val prestamos = PrestamosEntity(
            deudor = deudor,
            concepto = concepto,
            monto = monto.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            prestamosRepository.insert(prestamos)
            Limpiar()
        }
    }

    private fun Limpiar() {
        deudor = ""
        concepto = ""
        monto = ""
    }

}