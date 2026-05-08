package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	
	private String nombre;
	private ArrayList<Maquina> maquinas;
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private int ultimoCodigo = 100;
	
	// Constructor vacío
	public NegocioMejorado() {
		this.maquinas = new ArrayList<>();
	}
	
	// Constructor con parámetro
	public NegocioMejorado(String nombre) {
		this.nombre = nombre;
		this.maquinas = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}
	
	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	// Método generarCodigo
	public String generarCodigo() {
		// Generar número aleatorio entre 1 y 100
		int numeroAleatorio = (int) (Math.random() * 100) + 1;
		
		
		String codigo = "M-" + numeroAleatorio;
		return codigo;
	}
	
	// Método agregarMaquina
	public boolean agregarMaquina(String nombreCerveza, String descripcion, double precioPorMl) {
		// Generar código único
		String codigo = generarCodigo();
		
		
		Maquina maquinaExistente = recuperarMaquina(codigo);
		
	
		if (maquinaExistente != null) {
			return false;
		}
		
		
		Maquina nuevaMaquina = new Maquina(nombreCerveza, descripcion, precioPorMl, codigo);
		
		
		maquinas.add(nuevaMaquina);
		
		
		return true;
	}
	
	// Método cargarMaquinas
	public void cargarMaquinas() {
		
		for (int i = 0; i < maquinas.size(); i++) {
			
			Maquina maquina = maquinas.get(i);
			
			
			maquina.llenarMaquina();
		}
	}
	
	// Método recuperarMaquina
	public Maquina recuperarMaquina(String codigo) {
		
		for (int i = 0; i < maquinas.size(); i++) {
			
			Maquina maquina = maquinas.get(i);
			
		
			if (maquina.getCodigo().equals(codigo)) {
				return maquina;
			}
		}
	
		return null;
	}
	
	// Método registrarCliente
	public void registrarCliente(String nombre, String cedula) {
	
		Cliente nuevoCliente = new Cliente(nombre, cedula);
		
		
		nuevoCliente.setCodigo(ultimoCodigo);
		ultimoCodigo++;
		
		
		clientes.add(nuevoCliente);
	}
	
	// Método buscarClientePorCedula
	public Cliente buscarClientePorCedula(String cedula) {
		
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			
		
			if (cliente.getCedula().equals(cedula)) {
				return cliente;
			}
		}
		
		return null;
	}
	
	// Método buscarClientePorCodigo
	public Cliente buscarClientePorCodigo(int codigo) {
		
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			
			
			if (cliente.getCodigo() == codigo) {
				return cliente;
			}
		}
		
		
		return null;
	}
	
	// Método consumirCerveza
	public void consumirCerveza(int codigoCliente, String codigoMaquina, double cantidad) {
		
		Maquina maquina = recuperarMaquina(codigoMaquina);
		
		
		Cliente cliente = buscarClientePorCodigo(codigoCliente);
		
	
		if (maquina != null && cliente != null) {
			
			double valor = maquina.servirCerveza(cantidad);
			
			
			registrarConsumo(cliente, valor);
		}
	}
	
	// Método registrarConsumo
	public void registrarConsumo(Cliente cliente, double valor) {
		
		double totalActual = cliente.getTotalConsumido();
		
		
		double nuevoTotal = totalActual + valor;
		
	
		cliente.setTotalConsumido(nuevoTotal);
	}
	
	// Método consultarValorVendido
	public double consultarValorVendido() {
		
		double totalVendido = 0;
		
		
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			
		
			totalVendido = totalVendido + cliente.getTotalConsumido();
		}
		
		
		return totalVendido;
	}
	
}