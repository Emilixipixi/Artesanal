package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

public class TestConsumoMejorado {
	
	private static final double TOLERANCIA = 0.0001;
	
	@Test
	public void testConsumoActualizaCliente() {
		// Valida que el cliente se actualiza correctamente
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		// Agregar máquina
		negocio.agregarMaquina("Pilsener", "Cerveza rubia", 0.02);
		
		// Cargar máquina
		negocio.cargarMaquinas();
		
		// Registrar cliente
		negocio.registrarCliente("Juan", "1234567890");
		
		// Obtener datos del cliente
		int codigoCliente = negocio.buscarClientePorCedula("1234567890").getCodigo();
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		
		// Consumir cerveza
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 100);
		
		// Validar que el cliente fue actualizado
		double consumoEsperado = 100 * 0.02; // 2.0
		double consumoActual = negocio.buscarClientePorCedula("1234567890").getTotalConsumido();
		
		assertEquals(consumoEsperado, consumoActual, TOLERANCIA);
	}
	
	@Test
	public void testConsumoActualizaMaquina() {
		// Valida que la máquina se actualiza correctamente
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		// Agregar máquina
		negocio.agregarMaquina("Club", "Cerveza oscura", 0.03);
		
		// Cargar máquina (llena a capacidad-200)
		negocio.cargarMaquinas();
		
		// Registrar cliente
		negocio.registrarCliente("Pedro", "9876543210");
		
		// Obtener datos
		int codigoCliente = negocio.buscarClientePorCedula("9876543210").getCodigo();
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		double cantidadAntes = negocio.recuperarMaquina(codigoMaquina).getCantidadActual();
		
		// Consumir cerveza
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 500);
		
		// Validar que la máquina fue actualizada
		double cantidadDespues = negocio.recuperarMaquina(codigoMaquina).getCantidadActual();
		
		assertEquals(cantidadAntes - 500, cantidadDespues, TOLERANCIA);
	}
	
	@Test
	public void testConsumoValoresCorrectos() {
		// Valida que los valores de consumo sean correctos
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("IPA", "Cerveza amarga", 0.05);
		negocio.cargarMaquinas();
		negocio.registrarCliente("Ana", "1111111111");
		
		int codigoCliente = negocio.buscarClientePorCedula("1111111111").getCodigo();
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		
		// Primer consumo: 200 ml * 0.05 = 10.0
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 200);
		double totalConsumo1 = negocio.buscarClientePorCodigo(codigoCliente).getTotalConsumido();
		assertEquals(10.0, totalConsumo1, TOLERANCIA);
		
		// Segundo consumo: 300 ml * 0.05 = 15.0
		// Total acumulado: 25.0
		negocio.consumirCerveza(codigoCliente, codigoMaquina, 300);
		double totalConsumo2 = negocio.buscarClientePorCodigo(codigoCliente).getTotalConsumido();
		assertEquals(25.0, totalConsumo2, TOLERANCIA);
	}
	
	@Test
	public void testConsumoMultiplesClientes() {
		// Valida que múltiples clientes acumulan sus consumos independientemente
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		negocio.agregarMaquina("Stout", "Cerveza oscura fuerte", 0.08);
		negocio.cargarMaquinas();
		
		negocio.registrarCliente("Carlos", "2222222222");
		negocio.registrarCliente("Diana", "3333333333");
		
		int codigoCarlos = negocio.buscarClientePorCedula("2222222222").getCodigo();
		int codigoDiana = negocio.buscarClientePorCedula("3333333333").getCodigo();
		String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
		
		// Carlos consume 100 ml
		negocio.consumirCerveza(codigoCarlos, codigoMaquina, 100);
		
		// Diana consume 200 ml
		negocio.consumirCerveza(codigoDiana, codigoMaquina, 200);
		
		// Validar consumos independientes
		double consumoCarlos = negocio.buscarClientePorCodigo(codigoCarlos).getTotalConsumido();
		double consumoDiana = negocio.buscarClientePorCodigo(codigoDiana).getTotalConsumido();
		
		assertEquals(100 * 0.08, consumoCarlos, TOLERANCIA); // 8.0
		assertEquals(200 * 0.08, consumoDiana, TOLERANCIA);  // 16.0
	}
}