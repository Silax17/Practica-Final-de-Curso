package com.administrador.reservas.modelo;

public class Empleado {
	/**
	 * Clase encargada de representar un EMpleado del sistema de reservas.
	 */
	private int id;
	private String nombre;
	private String email;
	private String departamento;

	public Empleado() { // Constructor vacío
	}

	// Constructor completo
	public Empleado(int id, String nombre, String email, String departamento) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.departamento = departamento;

	}

	// Getters i Setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", departamento=" + departamento
				+ "]";
	}

}
