package com.example.marstest.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marstest.Model.Local.MarsDao
import com.example.marstest.Model.Remoto.TerrenoDeMArte
import com.example.marstest.Model.Remoto.RetrofitClient

class MarsRepository (private val marsDao: MarsDao) {



    private val retrofitClient = RetrofitClient.getRetrofit()

    val dataFromInternet = MutableLiveData<List<TerrenoDeMArte>>()



    //Forma con coroutinas

    suspend fun fetchDataFromInternetCoroutines() {
        try {
            val response = retrofitClient.fetchMarsDataCoroutines()
            when (response.code()) {
                //  in 200..299 -> dataFromInternet.value = response.body()
                in 200..299 -> response?.body().let {
                    if (it != null) {
                        // esta insertando en la base de datos Luego que creo la base de datos
                        marsDao.insertarTodosLosTerrenos(it)
                    }
                }

                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPO", "${response.code()} --- ${response.errorBody().toString()}")
            }
        } catch (t: Throwable) {
            Log.e("REPO", "${t.message}")
        }
    }



    //funciones del DAO


    fun  getTerrainByid(id:String) : LiveData<TerrenoDeMArte> {
        return getTerrainByid(id)
    }

    // listado de terrenos
    val listaTodosLosTerrenos: LiveData<List<TerrenoDeMArte>> =marsDao.obtenerTodosLosTerrenos()


    // insertar un terreno
    suspend fun insertarTerreno(mars: TerrenoDeMArte) {
        marsDao.insertarTerreno(mars)
    }

    // actualizar un terreno

    suspend fun updateTerrain(mars: TerrenoDeMArte) {
        marsDao.updateTerrain(mars)
    }

    // elimina terrenos
    suspend fun deleteAll() {
        marsDao.deleteAll()
    }
    //traer terreno por id??????

    fun getTerrain(id:Int):LiveData<TerrenoDeMArte>{
        return  marsDao.getMarsId(id)
    }}


