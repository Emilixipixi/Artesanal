package com.krakedev.artesanal;

public class Maquina {

	private String nombreCerveza;
	private String descripcion;
	private double precioPorMl;
	private double capacidadMaxima;
	private double cantidadActual;
	private String codigo;
	
	
	public Maquina () {};

	public Maquina(String nombreCerveza, String descripcion, double precioPorMl, double capacidadMaxima,String codigo) {

		this.nombreCerveza = nombreCerveza;
		this.descripcion = descripcion;
		this.precioPorMl = precioPorMl;
		this.capacidadMaxima = capacidadMaxima;
		this.cantidadActual = 0;
		this.codigo= codigo;

	}

	public Maquina(String nombreCerveza, String descripcion, double precioPorMl, String codigo) {

		this.nombreCerveza = nombreCerveza;
		this.descripcion = descripcion;
		this.precioPorMl = precioPorMl;
		this.capacidadMaxima = 10000;
		this.cantidadActual = 0;
		this.codigo= codigo;

	}

	public String getNombreCerveza() {
		return nombreCerveza;
	}

	public void setNombreCerveza(String nombreCerveza) {
		this.nombreCerveza = nombreCerveza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioPorMl() {
		return precioPorMl;
	}

	public void setPrecioPorMl(double precioPorMl) {
		this.precioPorMl = precioPorMl;
	}

	public double getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public double getCantidadActual() {
		return cantidadActual;
	}
	

	public String getCodigo() {
		return codigo;
	}
	
	
	
	
	
	
	
	

	public void imprimir() {
		String mensaje;
		mensaje = "Nombre Cerveza: " + nombreCerveza + " ,Descripcion: " + descripcion + " ,Precio por ml: "
				+ precioPorMl + " ,Capacidad Maxima: " + capacidadMaxima + " ,Cantidad Actual: " + cantidadActual+" ,Codigo: "+ codigo;
		System.out.println(mensaje);
	}

	public void llenarMaquina() {
		this.cantidadActual = this.capacidadMaxima - 200;

	}

	public boolean recargarCerveza(double cantidad) {

		double limitePermitido;
		limitePermitido = capacidadMaxima - 200;
		if (cantidadActual + cantidad <= limitePermitido) {

			cantidadActual = cantidadActual + cantidad;

			return true;

		} else {

			return false;
		}
	}
	
	public double servirCerveza (double cantidad) {
		
		if (cantidadActual >= cantidad) {
			
			cantidadActual = cantidadActual - cantidad;
			
			double valor;
			
			valor=cantidad*precioPorMl;
			
			return valor;
		}else {
			
			return 0;
		}
		 
		
	}

}
