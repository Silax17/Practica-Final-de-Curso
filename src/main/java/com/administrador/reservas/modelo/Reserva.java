package com.administrador.reservas.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
	private int id;
	private int empleado_id;
	private int sala_id;
	private LocalDate fecha;
	private LocalTime hora_inicio;
	private LocalTime hora_final;

	public Reserva() {
	}

	public Reserva(int id, int empleado_id, int sala_id, LocalDate fecha, LocalTime hora_inicio, LocalTime hora_final) {
		this.id = id;
		this.empleado_id = empleado_id;
		this.sala_id = sala_id;
		this.fecha = fecha;
		this.hora_inicio = hora_inicio;
		this.hora_final = hora_final;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpleado_id() {
		return empleado_id;
	}

	public void setEmpleado_id(int empleado_id) {
		this.empleado_id = empleado_id;
	}

	public int getSala_id() {
		return sala_id;
	}

	public void setSala_id(int sala_id) {
		this.sala_id = sala_id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public LocalTime getHora_final() {
		return hora_final;
	}

	public void setHora_final(LocalTime hora_final) {
		this.hora_final = hora_final;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", empleado_id=" + empleado_id + ", sala_id=" + sala_id + ", fecha=" + fecha
				+ ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + "]";
	}
	

}
