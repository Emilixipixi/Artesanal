package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestConsultarValorVendido {
	
	private static final double TOLERANCIA = 0.0001;
	
	@Test
	public void testValorVendidoSinConsumos() {
		// Valida que sin consumos, el total es 0
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		
		negocio.registrarCliente("Juan", "1234567890");
		
		// Sin consumos, el valor vendido debe ser 0
		double valorVendido = negocio.consultarValorVendido();
		
		assertEquals(0, valorVendido, TOLERANCIA);
	}
	
	@Test
	public void testValorVendidoUnCliente() {
		// Valida que con un cliente, se calcula correctamente
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("Club", "Cerveza oscura", 0.03);
		negocio.cargarMaquinas();
		
		negocio.registrarCliente("Pedro", "9876543210");
		
		int codigoCliente = negocio.buscarClientePorCedula("9876543210").getCodigo();
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		
		// Cliente consume 500 ml * 0.03 = 15.0
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 500);
		
		double valorVendido = negocio.consultarValorVendido();
		
		assertEquals(15.0, valorVendido, TOLERANCIA);
	}
	
	@Test
	public void testValorVendidoMultiplesClientes() {
		// Valida que con múltiples clientes, suma todos los consumos
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("IPA", "Cerveza amarga", 0.05);
		negocio.cargarMaquinas();
		
		// Registrar 3 clientes
		negocio.registrarCliente("Ana", "1111111111");
		negocio.registrarCliente("Bruno", "2222222222");
		negocio.registrarCliente("Clara", "3333333333");
		
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		
		// Ana consume 100 ml * 0.05 = 5.0
		int codigoAna = negocio.buscarClientePorCedula("1111111111").getCodigo();
		negocio.consumirCerveza(codigoAna, codigoMaquina, 100);
		
		// Bruno consume 200 ml * 0.05 = 10.0
		int codigoBruno = negocio.buscarClientePorCedula("2222222222").getCodigo();
		negocio.consumirCerveza(codigoBruno, codigoMaquina, 200);
		
		// Clara consume 300 ml * 0.05 = 15.0
		int codigoClara = negocio.buscarClientePorCedula("3333333333").getCodigo();
		negocio.consumirCerveza(codigoClara, codigoMaquina, 300);
		
		// Total esperado: 5.0 + 10.0 + 15.0 = 30.0
		double valorVendido = negocio.consultarValorVendido();
		
		assertEquals(30.0, valorVendido, TOLERANCIA);
	}
	
	@Test
	public void testValorVendidoMultiplesConsumosUnCliente() {
		// Valida que los consumos múltiples de un mismo cliente se suman
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("Stout", "Cerveza oscura fuerte", 0.08);
		negocio.cargarMaquinas();
		
		negocio.registrarCliente("David", "4444444444");
		
		int codigoCliente = negocio.buscarClientePorCedula("4444444444").getCodigo();
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		
		// Primer consumo: 100 ml * 0.08 = 8.0
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 100);
		
		// Segundo consumo: 150 ml * 0.08 = 12.0
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 150);
		
		// Tercer consumo: 200 ml * 0.08 = 16.0
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 200);
		
		// Total esperado: 8.0 + 12.0 + 16.0 = 36.0
		double valorVendido = negocio.consultarValorVendido();
		
		assertEquals(36.0, valorVendido, TOLERANCIA);
	}
}