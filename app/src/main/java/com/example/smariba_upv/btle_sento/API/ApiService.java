/********************************************
 * @file ApiService.java
 * @brief Interfaz para la API REST utilizada en la aplicación, donde se definen las llamadas GET y POST.
 * @version 1.0
 * @date 2024
 *******************************************/

package com.example.smariba_upv.btle_sento.API;

import com.example.smariba_upv.btle_sento.POJO.Medicion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/********************************************
 * @interface ApiService
 * @brief Interfaz que define las operaciones que se realizarán contra el servidor usando Retrofit.
 *******************************************/
public interface ApiService {

    /********************************************************
     * @fn Call<Object> ping()
     * @brief Método que realiza una petición GET al servidor para verificar su disponibilidad.
     * @return Retorna un objeto de tipo Call que contiene la respuesta del servidor.
     ********************************************************/
    @GET("/ping")
    Call<Object> ping(); // Aquí puedes usar una clase específica en lugar de Object para mapear la respuesta

    /********************************************************
     * @fn Call<Void> insertarMedicion(@Body Medicion medicion)
     * @brief Método que realiza una petición POST para insertar una medición en el servidor.
     * @param[in] medicion Objeto de tipo Medicion que contiene los datos de la medición a insertar.
     * @return Retorna un objeto de tipo Call<Void> que representa el resultado de la operación.
     ********************************************************/
    @POST("/insertar")
    Call<Void> insertarMedicion(@Body Medicion medicion);
}
