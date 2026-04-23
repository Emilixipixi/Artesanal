package com.krakedev.artesanal.testNegocio;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestNegocio {

	public static void main(String[] args) {
		
		Maquina nueva= new Maquina("Cerveza club", "Cerveza fria",0.02 , 8000, "0001");
		
		Negocio negocio1 = new Negocio("Mi negocio", nueva);
		
		
		System.out.println("Nombre : "+ negocio1.getNombre());
		System.out.println("Maquina : "+ negocio1.getMaquinaA());
		
		
		Maquina m1 = negocio1.getMaquinaA();
		double capacidad = m1.getCapacidadMaxima();
		

	}

}
