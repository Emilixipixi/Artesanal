package com.krakedev.artesanal.testNegocio;

import com.krakedev.artesanal.NegocioMejorado;

public class TestClientes {

	public static void main(String[] args) {
		
		// Instanciar NegocioMejorado
		NegocioMejorado negocio = new NegocioMejorado("Mi Bar");
		
		// Llamar a registrarCliente
		// Esto provocará NullPointerException porque clientes no está inicializado
		negocio.registrarCliente("Juan", "1234567890");
		
		System.out.println("Esto no se imprimirá");
	}

}