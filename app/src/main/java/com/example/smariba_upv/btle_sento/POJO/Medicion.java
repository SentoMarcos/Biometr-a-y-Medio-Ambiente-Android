/********************************************
 * @file Medicion.java
 * @brief Clase que representa una medición de gas en un lugar determinado.
 * @version 1.0
 * @date 2024
 *******************************************/

package com.example.smariba_upv.btle_sento.POJO;

/********************************************
 * @class Medicion
 * @brief Clase que encapsula los datos de una medición de gas, incluyendo el lugar, el tipo de gas y su valor.
 *******************************************/
public class Medicion {
    private String Lugar;
    private String Gas;
    private int Valor;

    /********************************************
     * @brief Constructor de la clase Medicion.
     * @param Lugar Nombre del lugar donde se realiza la medición.
     * @param Gas Tipo de gas medido.
     * @param Valor Valor medido del gas.
     *******************************************/
    public Medicion(String Lugar, String Gas, int Valor) {
        this.Lugar = Lugar;
        this.Gas = Gas;
        this.Valor = Valor;
    }

    /********************************************
     * @brief Obtiene el lugar de la medición.
     * @return Un String que representa el lugar.
     *******************************************/
    public String getLugar() {
        return Lugar;
    }

    /********************************************
     * @brief Establece el lugar de la medición.
     * @param lugar String que representa el lugar.
     *******************************************/
    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    /********************************************
     * @brief Obtiene el tipo de gas medido.
     * @return Un String que representa el tipo de gas.
     *******************************************/
    public String getGas() {
        return Gas;
    }

    /********************************************
     * @brief Establece el tipo de gas medido.
     * @param gas String que representa el tipo de gas.
     *******************************************/
    public void setGas(String gas) {
        Gas = gas;
    }

    /********************************************
     * @brief Obtiene el valor de la medición del gas.
     * @return Un entero que representa el valor medido.
     *******************************************/
    public int getValor() {
        return Valor;
    }

    /********************************************
     * @brief Establece el valor de la medición del gas.
     * @param valor Entero que representa el valor medido.
     *******************************************/
    public void setValor(int valor) {
        Valor = valor;
    }
}
