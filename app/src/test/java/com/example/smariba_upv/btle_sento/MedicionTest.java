package com.example.smariba_upv.btle_sento;

import static org.junit.Assert.assertEquals;

import com.example.smariba_upv.btle_sento.POJO.Medicion;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class MedicionTest {
    @Test
    @DisplayName("Test de la clase Medicion")
    public void testMedicion() {
        Medicion medicion = new Medicion("Laboratorio", "CO2", 100);
        assertEquals("Laboratorio", medicion.getLugar());
        assertEquals("CO2", medicion.getGas());
        assertEquals(100, medicion.getValor());
        // mostrar resultado
        System.out.println("Lugar: " + medicion.getLugar());
        System.out.println("Gas: " + medicion.getGas());
        System.out.println("Valor: " + medicion.getValor());
    }

}
