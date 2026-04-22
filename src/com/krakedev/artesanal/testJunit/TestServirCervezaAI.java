package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAI {

    private static final double TOLERANCIA = 0.0001;

    @Test
    void testServirCervezaSuficienteCantidad() {
        // Valida que si hay suficiente cerveza, se descuenta y se calcula el valor correctamente

        Maquina maquina = new Maquina("Pilsener", "Rubia", 0.05, 1000,"0001");
        maquina.recargarCerveza(500); // cantidad actual: 500

        double valor = maquina.servirCerveza(200);

        assertEquals(10.0, valor, TOLERANCIA); // 200 * 0.05
        assertEquals(300, maquina.getCantidadActual(), TOLERANCIA); // 500 - 200
    }

    @Test
    void testServirCervezaCantidadExacta() {
        // Valida que si la cantidad solicitada es exactamente la disponible, se sirve todo

        Maquina maquina = new Maquina("IPA", "Amarga", 0.1, 1000,"0001");
        maquina.recargarCerveza(300); // cantidad actual: 300

        double valor = maquina.servirCerveza(300);

        assertEquals(30.0, valor, TOLERANCIA); // 300 * 0.1
        assertEquals(0, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    void testServirCervezaSinSuficienteCantidad() {
        // Valida que si no hay suficiente cerveza, no sirve y retorna 0

        Maquina maquina = new Maquina("Stout", "Oscura", 0.08, 1000,"0001");
        maquina.recargarCerveza(100); // cantidad actual: 100

        double valor = maquina.servirCerveza(200);

        assertEquals(0, valor, TOLERANCIA);
        assertEquals(100, maquina.getCantidadActual(), TOLERANCIA); // no cambia
    }

    @Test
    void testServirCervezaSinCantidadDisponible() {
        // Valida que si la máquina está vacía, no sirve nada

        Maquina maquina = new Maquina("Lager", "Suave", 0.05, 1000,"0001");
        // cantidadActual = 0 por defecto

        double valor = maquina.servirCerveza(100);

        assertEquals(0, valor, TOLERANCIA);
        assertEquals(0, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    void testServirCervezaConstructorSinCapacidad() {
        // Valida comportamiento usando el constructor que asigna capacidad por defecto

        Maquina maquina = new Maquina("Porter", "Intensa", 0.07,"0001");
        maquina.recargarCerveza(500); // usa capacidad por defecto

        double valor = maquina.servirCerveza(100);

        assertEquals(7.0, valor, TOLERANCIA); // 100 * 0.07
        assertEquals(400, maquina.getCantidadActual(), TOLERANCIA);
    }

    @Test
    void testServirCervezaCantidadCero() {
        // Valida que si se solicita 0 ml, no cambia nada y el valor es 0

        Maquina maquina = new Maquina("Amber", "Media", 0.06, 1000,"0001");
        maquina.recargarCerveza(200);

        double valor = maquina.servirCerveza(0);

        assertEquals(0, valor, TOLERANCIA);
        assertEquals(200, maquina.getCantidadActual(), TOLERANCIA);
    }
}