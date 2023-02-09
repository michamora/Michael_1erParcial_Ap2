package com.ucne.proj_1erparcial_ap2.data.local.dao

import androidx.room.*
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestamosDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(prestamos: PrestamosEntity) //Guardar

        @Query("""                           
            Select * From Prestamos
            Order By prestamosId desc
        """)
        fun getList(): Flow<List<PrestamosEntity>>  //Obtener lista

    }
    class dao{
        fun save():Boolean{
            return true
        }
    }



