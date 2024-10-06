/********************************************
 * @file RetrofitClient.java
 * @brief Clase encargada de gestionar la instancia de Retrofit para interactuar con el servidor.
 * @version 1.0
 * @date 2024
 *******************************************/

package com.example.smariba_upv.btle_sento.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/********************************************
 * @class RetrofitClient
 * @brief Clase que proporciona la instancia de Retrofit configurada para realizar llamadas a la API REST.
 *******************************************/
public class RetrofitClient {

    /********************************************
     * @var retrofit
     * @brief Instancia única de Retrofit, utilizada para realizar las solicitudes a la API.
     *******************************************/
    private static Retrofit retrofit = null;

    /********************************************************
     * @fn ApiService getApiService()
     * @brief Método estático que devuelve una instancia de ApiService para hacer llamadas a la API.
     *
     * Este método comprueba si la instancia de Retrofit ya ha sido creada. Si no lo ha sido, la crea y configura.
     * Utiliza el patrón Singleton para asegurarse de que solo se cree una instancia de Retrofit.
     *
     * @return Retorna una instancia de ApiService que contiene las definiciones de las operaciones API.
     ********************************************************/
    public static ApiService getApiService() {
        if (retrofit == null) {
            // Construye la instancia de Retrofit con la URL base y el convertidor Gson
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.19:3000/") // Cambia la IP por la de tu servidor
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        // Retorna la instancia de ApiService creada a partir de Retrofit
        return retrofit.create(ApiService.class);
    }
}
