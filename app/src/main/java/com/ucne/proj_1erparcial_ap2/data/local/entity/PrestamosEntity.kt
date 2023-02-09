package com.ucne.proj_1erparcial_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prestamos")

data class PrestamosEntity( // Entidad

    @PrimaryKey(autoGenerate = true)

    val prestamosId: Int? = null,
    val deudor: String,
    val concepto: String,
    val monto: Double
)