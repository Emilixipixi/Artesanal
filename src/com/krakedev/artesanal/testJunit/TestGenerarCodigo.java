package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestGenerarCodigo {
	
	@Test
	public void testFormatoCodigoGenerado() {
		// Valida que el código tenga el formato M-XX
		NegocioMejorado negocio = new NegocioMejorado("Mi Negocio");
		
		String codigo = negocio.generarCodigo();
		
		// Debe empezar con "M-"
		assertTrue(codigo.startsWith("M-"));
		
		// Debe tener longitud entre 3 y 4 caracteres (M-1 hasta M-100)
		assertTrue(codigo.length() >= 3 && codigo.length() <= 4);
	}
	
	@Test
	public void testCodigoEsAleatorio() {
		// Valida que cada llamada genera un código diferente (con alta probabilidad)
		NegocioMejorado negocio = new NegocioMejorado("Mi Negocio");
		
		String codigo1 = negocio.generarCodigo();
		String codigo2 = negocio.generarCodigo();
		String codigo3 = negocio.generarCodigo();
		
		// Al menos dos deben ser diferentes (probabilidad muy alta)
		boolean sonDiferentes = !codigo1.equals(codigo2) || !codigo2.equals(codigo3);
		assertTrue(sonDiferentes);
	}
	
	@Test
	public void testCodigoEnRango() {
		// Valida que el número esté entre 1 y 100
		NegocioMejorado negocio = new NegocioMejorado("Mi Negocio");
		
		for (int i = 0; i < 20; i++) {
			String codigo = negocio.generarCodigo();
			
			// Extraer el número del código (ej: "M-25" → 25)
			String numero = codigo.substring(2);
			int num = Integer.parseInt(numero);
			
			// Validar que esté en el rango 1-100
			assertTrue(num >= 1 && num <= 100, "Código fuera de rango: " + codigo);
		}
	}
}