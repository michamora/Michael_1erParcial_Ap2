package com.ucne.proj_1erparcial_ap2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamosDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity

@Database(
    entities = [
        PrestamosEntity::class
    ],
    version = 1
)
abstract class PrestamosDb: RoomDatabase() {
    abstract val prestamosDao: PrestamosDao
}