package com.administrador.reservas.dao;

public class Sala {
	private int id;
	private String nombre;
	private int capacidad;
	private String recursos_disponibles;
	
	public Sala() {}

	public Sala(int id, String nombre, int capacidad, String recursos_disponibles) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.recursos_disponibles = recursos_disponibles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getRecursos_disponibles() {
		return recursos_disponibles;
	}

	public void setRecursos_disponibles(String recursos_disponibles) {
		this.recursos_disponibles = recursos_disponibles;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + ", capacidad=" + capacidad + ", recursos_disponibles="
				+ recursos_disponibles + "]";
	}

}
