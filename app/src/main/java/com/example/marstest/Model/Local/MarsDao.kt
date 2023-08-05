package com.example.marstest.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marstest.Model.Remoto.TerrenoDeMArte

@Dao
interface MarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTerreno(mars: TerrenoDeMArte)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodosLosTerrenos(mars: List<TerrenoDeMArte>)



    @Update
    suspend fun updateTerrain(mars: TerrenoDeMArte)

    @Delete
    suspend fun  deleteTerrain(mars: TerrenoDeMArte)


    @Query("DELETE FROM mars_table")
    suspend fun deleteAll()


    // traer todos los terrenos

    @Query("SELECT * FROM mars_table ORDER BY id DESC")
    fun obtenerTodosLosTerrenos(): LiveData<List<TerrenoDeMArte>>


    @Query("SELECT * FROM mars_table WHERE type=:type Limit 1")
    fun getMarsType( type: String): LiveData<TerrenoDeMArte>


    @Query("SELECT * FROM mars_table WHERE id=:id")
    fun getMarsId(id:Int): LiveData<TerrenoDeMArte>

}