/********************************************
 * @file TramaIBeacon.java
 * @brief Clase que representa una trama iBeacon, extrayendo sus diferentes campos a partir de un array de bytes.
 * @version 1.0
 * @date 2024
 *******************************************/

package com.example.smariba_upv.btle_sento.POJO;

import java.util.Arrays;

/********************************************
 * @class TramaIBeacon
 * @brief Clase que modela los datos de una trama iBeacon y permite acceder a sus diferentes componentes, tales como UUID, Major, Minor y más.
 *******************************************/
public class TramaIBeacon {
    private byte[] prefijo = null; // 9 bytes
    private byte[] uuid = null; // 16 bytes
    private byte[] major = null; // 2 bytes
    private byte[] minor = null; // 2 bytes
    private byte txPower = 0; // 1 byte

    private byte[] losBytes;

    private byte[] advFlags = null; // 3 bytes
    private byte[] advHeader = null; // 2 bytes
    private byte[] companyID = new byte[2]; // 2 bytes
    private byte iBeaconType = 0; // 1 byte
    private byte iBeaconLength = 0; // 1 byte

    /********************************************
     * @brief Constructor de la clase TramaIBeacon.
     * @param bytes Array de bytes que contiene los datos de la trama iBeacon.
     *******************************************/
    public TramaIBeacon(byte[] bytes) {
        this.losBytes = bytes;

        prefijo = Arrays.copyOfRange(losBytes, 0, 8 + 1); // 9 bytes
        uuid = Arrays.copyOfRange(losBytes, 9, 24 + 1); // 16 bytes
        major = Arrays.copyOfRange(losBytes, 25, 26 + 1); // 2 bytes
        minor = Arrays.copyOfRange(losBytes, 27, 28 + 1); // 2 bytes
        txPower = losBytes[29]; // 1 byte

        advFlags = Arrays.copyOfRange(prefijo, 0, 2 + 1); // 3 bytes
        advHeader = Arrays.copyOfRange(prefijo, 3, 4 + 1); // 2 bytes
        companyID = Arrays.copyOfRange(prefijo, 5, 6 + 1); // 2 bytes
        iBeaconType = prefijo[7]; // 1 byte
        iBeaconLength = prefijo[8]; // 1 byte
    }

    /********************************************
     * @fn public byte[] getPrefijo()
     * @brief Obtiene el prefijo de la trama iBeacon.
     * @return Un array de bytes que representa el prefijo.
     *******************************************/
    public byte[] getPrefijo() {
        return prefijo;
    }

    /********************************************
     * @fn public byte[] getUUID()
     * @brief Obtiene el UUID de la trama iBeacon.
     * @return Un array de bytes que representa el UUID.
     *******************************************/
    public byte[] getUUID() {
        return uuid;
    }

    /********************************************
     * @fn public byte[] getMajor()
     * @brief Obtiene el campo Major de la trama iBeacon.
     * @return Un array de bytes que representa el campo Major.
     *******************************************/
    public byte[] getMajor() {
        return major;
    }

    /********************************************
     * @fn public byte[] getMinor()
     * @brief Obtiene el campo Minor de la trama iBeacon.
     * @return Un array de bytes que representa el campo Minor.
     *******************************************/
    public byte[] getMinor() {
        return minor;
    }

    /********************************************
     * @fn public byte getTxPower()
     * @brief Obtiene el valor de Tx Power de la trama iBeacon.
     * @return Un byte que representa el valor de Tx Power.
     *******************************************/
    public byte getTxPower() {
        return txPower;
    }

    /********************************************
     * @fn public byte[] getLosBytes()
     * @brief Obtiene el array completo de bytes de la trama iBeacon.
     * @return Un array de bytes que representa la trama completa.
     *******************************************/
    public byte[] getLosBytes() {
        return losBytes;
    }

    /********************************************
     * @fn public byte[] getAdvFlags()
     * @brief Obtiene los flags de la trama de advertising.
     * @return Un array de bytes que representa los flags de advertising.
     *******************************************/
    public byte[] getAdvFlags() {
        return advFlags;
    }

    /********************************************
     * @fn public byte[] getAdvHeader()
     * @brief Obtiene el header de la trama de advertising.
     * @return Un array de bytes que representa el header de advertising.
     *******************************************/
    public byte[] getAdvHeader() {
        return advHeader;
    }

    /********************************************
     * @fn public byte[] getCompanyID()
     * @brief Obtiene el ID de la compañía en la trama iBeacon.
     * @return Un array de bytes que representa el Company ID.
     *******************************************/
    public byte[] getCompanyID() {
        return companyID;
    }

    /********************************************
     * @fn public byte getiBeaconType()
     * @brief Obtiene el tipo de iBeacon.
     * @return Un byte que representa el tipo de iBeacon.
     *******************************************/
    public byte getiBeaconType() {
        return iBeaconType;
    }

    /********************************************
     * @fn public byte getiBeaconLength()
     * @brief Obtiene la longitud de los datos iBeacon.
     * @return Un byte que representa la longitud del iBeacon.
     *******************************************/
    public byte getiBeaconLength() {
        return iBeaconLength;
    }
} // class
