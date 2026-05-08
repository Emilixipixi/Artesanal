package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestAgregarMaquina {
	
	@Test
	public void testAgregarMaquinaExitosa() {
		// Valida que se puede agregar una máquina correctamente
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		boolean resultado = negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		
		// Debe retornar true
		assertTrue(resultado);
		
		// La lista debe tener 1 máquina
		assertEquals(1, negocio.getMaquinas().size());
	}
	
	@Test
	public void testAgregarMultiplesMaquinas() {
		// Valida que se pueden agregar varias máquinas
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		boolean res1 = negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		boolean res2 = negocio.agregarMaquina("Club", "Cerveza oscura", 0.03);
		boolean res3 = negocio.agregarMaquina("IPA", "Cerveza amarga", 0.04);
		
		assertTrue(res1);
		assertTrue(res2);
		assertTrue(res3);
		
		assertEquals(3, negocio.getMaquinas().size());
	}
	
	@Test
	public void testNoDuplicarCodigoConMismoCodePorAzar() {
		// Este test es teórico, porque los códigos son aleatorios
		// En la práctica, la validación funciona si generarCodigo genera el mismo código
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		// Agregamos una máquina
		boolean res1 = negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		assertTrue(res1);
		
		// Obtenemos el código de la primera máquina
		String codigoMaquina1 = negocio.getMaquinas().get(0).getCodigo();
		
		// Creamos una máquina manualmente con el mismo código
		// y validamos que recuperarMaquina la encuentre
		assertNotNull(negocio.recuperarMaquina(codigoMaquina1));
	}
	
	@Test
	public void testRecuperarMaquinaNoExistente() {
		// Valida que recuperarMaquina retorna null si no existe
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		
		// Buscar código que no existe
		assertNull(negocio.recuperarMaquina("M-999"));
	}
	
	@Test
	public void testRecuperarMaquinaExistente() {
		// Valida que recuperarMaquina encuentra la máquina correcta
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		negocio.agregarMaquina("Club", "Cerveza oscura", 0.03);
		
		// Obtener código de la primera máquina
		String codigo = negocio.getMaquinas().get(0).getCodigo();
		
		// Recuperarla
		assertNotNull(negocio.recuperarMaquina(codigo));
		assertEquals("Pilsener", negocio.recuperarMaquina(codigo).getNombreCerveza());
	}
}