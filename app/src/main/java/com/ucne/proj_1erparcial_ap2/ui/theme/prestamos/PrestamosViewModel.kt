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
    var deudorError by mutableStateOf("")

    var concepto by mutableStateOf("")
    var conceptoError by mutableStateOf("")

    var monto by mutableStateOf("")
    var montoError by mutableStateOf("")

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

    fun onDeudorChanged(deudor: String) {
        this.deudor = deudor
        HayErrores()
    }

    fun onConceptoChanged(concepto: String) {
        this.concepto = concepto
        HayErrores()
    }

    fun onMontoChanged(monto: String) {
        this.monto = monto
        HayErrores()
    }

    fun insertar() {

        if (HayErrores())
            return

        val prestamo = PrestamosEntity(
            deudor = deudor,
            concepto = concepto,
            monto = monto.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            prestamosRepository.insert(prestamo)
            Limpiar()
        }
    }

    private fun HayErrores(): Boolean {
        var hayError = false
        deudorError = ""
        if (deudor.isBlank()) {
            deudorError = "  Debe indicar el deudor"
            hayError = true
        }

        conceptoError = ""
        if (concepto.isBlank()) {
            conceptoError = "  Debe indicar el concepto"
            hayError = true
        }

        montoError = ""
        if ((monto.toDoubleOrNull() ?: 0.0) <= 0.0) {
            montoError = "  Debe indicar un monto mayor que cero"
            hayError = true
        }
        return hayError
    }

    private fun Limpiar() {
        deudor = ""
        concepto = ""
        monto = ""
    }

}