/********************************************
 * @file Utilidades.java
 * @brief Clase con métodos utilitarios para manipulación de bytes, UUIDs y conversión de tipos.
 * @version 1.0
 * @date 2024
 *******************************************/

package com.example.smariba_upv.btle_sento.LOGIC;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

/********************************************
 * @class Utilidades
 * @brief Clase con métodos estáticos para la manipulación de datos en forma de bytes, cadenas, y UUIDs.
 *******************************************/
public class Utilidades {

    /********************************************************
     * @fn public static byte[] stringToBytes(String texto)
     * @brief Convierte un string en un array de bytes.
     *
     * @param texto El string que se quiere convertir.
     * @return El array de bytes que representa el string.
     ********************************************************/
    public static byte[] stringToBytes(String texto) {
        return texto.getBytes();
    }

    /********************************************************
     * @fn public static UUID stringToUUID(String uuid)
     * @brief Convierte un string de 16 caracteres en un objeto UUID.
     *
     * @param uuid El string que se desea convertir a UUID.
     * @return El objeto UUID generado a partir del string.
     * @throw Error Si el string no tiene exactamente 16 caracteres.
     ********************************************************/
    public static UUID stringToUUID(String uuid) {
        if (uuid.length() != 16) {
            throw new Error("stringUUID: string no tiene 16 caracteres");
        }
        String masSignificativo = uuid.substring(0, 8);
        String menosSignificativo = uuid.substring(8, 16);
        return new UUID(Utilidades.bytesToLong(masSignificativo.getBytes()), Utilidades.bytesToLong(menosSignificativo.getBytes()));
    }

    /********************************************************
     * @fn public static String uuidToString(UUID uuid)
     * @brief Convierte un objeto UUID en un string.
     *
     * @param uuid El objeto UUID a convertir.
     * @return El string que representa al UUID.
     ********************************************************/
    public static String uuidToString(UUID uuid) {
        return bytesToString(dosLongToBytes(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
    }

    /********************************************************
     * @fn public static String uuidToHexString(UUID uuid)
     * @brief Convierte un objeto UUID en un string hexadecimal.
     *
     * @param uuid El objeto UUID a convertir.
     * @return El string hexadecimal que representa al UUID.
     ********************************************************/
    public static String uuidToHexString(UUID uuid) {
        return bytesToHexString(dosLongToBytes(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
    }

    /********************************************************
     * @fn public static String bytesToString(byte[] bytes)
     * @brief Convierte un array de bytes en un string.
     *
     * @param bytes El array de bytes a convertir.
     * @return El string generado a partir del array de bytes.
     ********************************************************/
    public static String bytesToString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    /********************************************************
     * @fn public static byte[] dosLongToBytes(long masSignificativos, long menosSignificativos)
     * @brief Convierte dos valores `long` en un array de bytes.
     *
     * @param masSignificativos Parte más significativa del valor.
     * @param menosSignificativos Parte menos significativa del valor.
     * @return El array de bytes que representa los dos valores long.
     ********************************************************/
    public static byte[] dosLongToBytes(long masSignificativos, long menosSignificativos) {
        ByteBuffer buffer = ByteBuffer.allocate(2 * Long.BYTES);
        buffer.putLong(masSignificativos);
        buffer.putLong(menosSignificativos);
        return buffer.array();
    }

    /********************************************************
     * @fn public static int bytesToInt(byte[] bytes)
     * @brief Convierte un array de bytes en un entero.
     *
     * @param bytes El array de bytes a convertir.
     * @return El valor entero resultante de la conversión.
     ********************************************************/
    public static int bytesToInt(byte[] bytes) {
        return new BigInteger(bytes).intValue();
    }

    /********************************************************
     * @fn public static long bytesToLong(byte[] bytes)
     * @brief Convierte un array de bytes en un valor `long`.
     *
     * @param bytes El array de bytes a convertir.
     * @return El valor `long` resultante de la conversión.
     ********************************************************/
    public static long bytesToLong(byte[] bytes) {
        return new BigInteger(bytes).longValue();
    }

    /********************************************************
     * @fn public static int bytesToIntOK(byte[] bytes)
     * @brief Convierte un array de bytes en un entero, con manejo de signos.
     *
     * @param bytes El array de bytes a convertir.
     * @return El valor entero resultante de la conversión.
     * @throw Error Si el array de bytes es demasiado largo para un entero.
     ********************************************************/
    public static int bytesToIntOK(byte[] bytes) {
        if (bytes == null) {
            return 0;
        }

        if (bytes.length > 4) {
            throw new Error("demasiados bytes para pasar a int");
        }

        int res = 0;
        for (byte b : bytes) {
            res = (res << 8) + (b & 0xFF); // Asegurarse de obtener solo los 8 bits menos significativos de cada byte
        }

        if ((bytes[0] & 0x8) != 0) { // Si el número es negativo
            res = -(~(byte) res) - 1; // Realiza el complemento a 2
        }

        return res;
    }

    /********************************************************
     * @fn public static String bytesToHexString(byte[] bytes)
     * @brief Convierte un array de bytes en un string hexadecimal.
     *
     * @param bytes El array de bytes a convertir.
     * @return El string hexadecimal generado a partir del array de bytes.
     ********************************************************/
    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
            sb.append(':');
        }
        return sb.toString();
    }

} // class
