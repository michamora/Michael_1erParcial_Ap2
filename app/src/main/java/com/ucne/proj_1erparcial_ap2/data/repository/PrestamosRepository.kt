package com.ucne.proj_1erparcial_ap2.data.repository

import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamosDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrestamosRepository@Inject constructor(
    private  val prestamosDao: PrestamosDao
) {
    suspend fun insert(prestamos: PrestamosEntity) {
        return prestamosDao.insert(prestamos)
    }

    fun getList(): Flow<List<PrestamosEntity>> = prestamosDao.getList()
}
