package com.administrador.reservas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.administrador.reservas.dao.EmpleadoDAO;
import com.administrador.reservas.dao.ReservaDAO;
import com.administrador.reservas.dao.SalaDAO;
import com.administrador.reservas.modelo.Empleado;
import com.administrador.reservas.modelo.Reserva;
import com.administrador.reservas.modelo.Sala;

public class Main {

	private static final Scanner Strscan = new Scanner(System.in);
	private static final Scanner Intscan = new Scanner(System.in);
	private static final SalaDAO salaDAO = new SalaDAO();
	private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
	private static final ReservaDAO reservaDAO = new ReservaDAO();

	public static void main(String args[]) throws SQLException {
		int opcion; // Menu para llamar a otros menus
		do {
			System.out.println("\nMenu");
			System.out.println("1.Menu Sala");
			System.out.println("2.Menu Empleado");
			System.out.println("3.Menu Reserva");
			System.out.println("0.Salir");
			opcion = Intscan.nextInt();

			switch (opcion) { // Llamar a los otros menu con funciones
			case 1:
				menuSalas();
				break;
			case 2:
				menuEmpleados();
				break;
			case 3:
				menuReserva();
				break;

			}

		} while (opcion != 0);
		{
			System.out.println("Saliendo...");
		}
	}

	public static void menuSalas() { // menu sala para gestionar
		int opcion;
		do {
			System.out.println("\nGestionar Salas");
			System.out.println("1.Crear Sala");
			System.out.println("2.Ver Salas");
			System.out.println("3.Actualizar Salas");
			System.out.println("4.Borrar Sala");
			System.out.println("0. Volver al menu principal");
			opcion = Intscan.nextInt();

			switch (opcion) { // Llamar las funciones para las opciones
			case 1:
				crearSala();
				break;
			case 2:
				verSalas();
				break;
			case 3:
				actualizarSala();
				break;
			case 4:
				eliminarSala();
				break;

			}

		} while (opcion != 0);
		{
			System.out.println("Saliendo...");
		}

	}

	public static void menuEmpleados() { // Menu para gestionar Empleados
		int opcion;
		do {
			System.out.println("\nGestionar Empleados");
			System.out.println("1.Alta Empleado");
			System.out.println("2.Ver Empleados");
			System.out.println("3.Actualizar Empleado");
			System.out.println("4.Baja Empleado");
			System.out.println("0. Volver al menu principal");
			opcion = Intscan.nextInt();

			switch (opcion) { // Llamar las funciones para las Opciones
			case 1:
				crearEmpleado();
				break;
			case 2:
				verEmpleado();
				break;
			case 3:
				actualizarEmp();
				break;
			case 4:
				eliminarEmp();
				break;

			}

		} while (opcion != 0);
		{
			System.out.println("Saliendo...");
		}

	}

	public static void menuReserva() {// Menu para gestionar Reservas
		int opcion;
		do {
			System.out.println("\nGestionar Reservas");
			System.out.println("1.Reservar");
			System.out.println("2.Ver Reservas");
			System.out.println("3.Actualizar Reserva");
			System.out.println("4.Quitar Reserva");
			System.out.println("0. Volver al menu principal");
			opcion = Intscan.nextInt();

			switch (opcion) {// Llamar las funciones para las opciones
			case 1:
				crearReserva();
				break

				;
			case 2:
				verReservas();
				break

				;
			case 3:
				actualizarReserva();
				break

				;
			case 4:
				eliminarReserva();
				break

				;

			}

		} while (opcion != 0);
		{
			System.out.println("Saliendo...");
		}

	}

	public static void crearSala() { // Crea la sala y llama a la clase SalaDAO para crearla
		try {
			System.out.println("Nombre de la Sala: ");
			String nombre = Strscan.nextLine();

			System.out.println("Capacidad de la Sala: ");
			int capacidad = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("Recursos Disponibles en la Sala: ");
			String rec_disp = Strscan.nextLine();

			Sala sala = new Sala(0, nombre, capacidad, rec_disp);
			salaDAO.agregarSala(sala);

			System.out.println("Sala creada");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void verSalas() { // Obtiene las salas llamando a la clase Sala DAO
		try {
			for (Sala sala : salaDAO.verSalas()) {
				System.out.println(sala);

			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void actualizarSala() { // Modifica el contenido llamando a la clase SalaDAO
		try {
			verSalas();
			System.out.println("Actualizar Sala con ID: ");
			int id = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("Nombre que desea: ");
			String nombre = Strscan.nextLine();

			System.out.println("Capacidad que desea: ");
			int capacidad = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("Recursos que desea: ");
			String recursos = Strscan.nextLine();

			System.out.println("Se va actualizar la sala...");

			Sala sala = new Sala(id, nombre, capacidad, recursos);
			salaDAO.actualizarSala(sala);
			System.out.println("Sala Actualizada");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();

		}
	}

	public static void eliminarSala() { // Elimina la sala llamando a la clase SalaDAO
		try {
			verSalas();
			System.out.println("Eliminar Sala con ID: ");
			int id = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("Eliminando Sala y sus reservas");
			salaDAO.eliminarSalaRes(id);
			System.out.println("Sala Eliminada");

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();

		}

	}

	public static void crearEmpleado() { // Crea el empleado llamando a la clase EmpleadoDAO
		try {
			System.out.println("Nombre del Empleado: ");
			String nombre = Strscan.nextLine();
			if (nombre.trim().split("\\s+").length < 2) {
				System.out.println("Debes ingresar al menos nombre y apellido.");
				System.out.println("Volviendo al menu...");
				return;
			}
			System.out.println("Email del Empleado: ");
			String email = Strscan.nextLine();
			if (!email.contains("@")) {
				System.out.println("Email inválido. Debe contener '@'.");
				System.out.println("Volviendo al menu...");
				return;
			}

			System.out.println("Departamento del Empleado: ");
			String depart = Strscan.nextLine();

			Empleado empleado = new Empleado(0, nombre, email, depart);
			empleadoDAO.agregarEmpleado(empleado);
			System.out.println("Empleado dado de Alta");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void verEmpleado() { // Enseña los empleados llamando a la clase empleadoDAO
		try {

			for (Empleado empleado : empleadoDAO.verEmpleados()) {
				System.out.println(empleado);

			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public static void actualizarEmp() {// Actualiza el contenido de un empleado llamando a la clase EmpeladoDAO
		try {
			verEmpleado();
			System.out.println("Actualizar Empleado con ID: ");
			int id = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("Nombre del Empleado: ");
			String nombre = Strscan.nextLine();

			System.out.println("Email del Empleado: ");
			String email = Strscan.nextLine();
			;

			System.out.println("Departamento del Empleado: ");
			String departamento = Strscan.nextLine();

			System.out.println("Se va actualizar la sala...");

			Empleado empleado = new Empleado(id, nombre, email, departamento);
			if (empleadoDAO.validarEmpleado(empleado)) {
				empleadoDAO.actualizarEmpleados(empleado);
				System.out.println("Datos Actualizados");
			} else {
				System.out.println("Datos inválidos, no se actualizó el empleado.");
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void eliminarEmp() {
		try {
			verEmpleado();
			System.out.println("Empleado que quieras eliminar con ID: ");
			int id = Intscan.nextInt();
			System.out.println("Eliminando Empleado y sus reservas...");
			empleadoDAO.eliminarEmpleadoRes(id);
			System.out.println("Empleado y reservas eliminadas correctamente.");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void crearReserva() {// Crear la reserva a partir de la clase ReservaDAO y los id de sala y empleado
		try {
			System.out.println("ID del Empleado: ");
			int emp_id = Intscan.nextInt();

			System.out.println("ID de la Sala: ");
			int sala_id = Intscan.nextInt();

			System.out.println("Fecha de la reserva (yyyy-MM-dd)");
			LocalDate fecha = LocalDate.parse(Strscan.nextLine());

			System.out.println("Hora inicio (HH:mm)");
			LocalTime horaInicio = LocalTime.parse(Strscan.nextLine());

			System.out.println("Hora final (HH:mm)");
			LocalTime horaFinal = LocalTime.parse(Strscan.nextLine());

			if (reservaDAO.conflictoHorario(sala_id, fecha, horaInicio, horaFinal)) {
				System.out.println("Error: La sala ya está reservada en ese horario.");
			} else {
				Reserva reserva = new Reserva(0, emp_id, sala_id, fecha, horaInicio, horaFinal);
				reservaDAO.agregarReserva(reserva);
				System.out.println("Reserva creada");

			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public static void verReservas() { // Muestra las reservas llamando a la funcion
		try {

			for (Reserva reservas : reservaDAO.verReserva()) {
				System.out.println(reservas);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public static void actualizarReserva() { // Modifica el contenido de la reserva
		try {
			verReservas();
			System.out.println(" ");
			verSalas();
			System.out.println(" ");
			verEmpleado();
			System.out.println("ID de la Reserva para Actualizar:");
			System.out.println("Tienen que existir los ID de sala y empleado sino no funciona");
			int id = Intscan.nextInt();
			Intscan.nextLine();

			Reserva reservaCompr = reservaDAO.obtenerReservaPorId(id);
			if (reservaCompr == null) {
				System.out.println("Error: No existe una reserva con ese ID.");
				return;
			}

			System.out.println("ID del Empleado:");
			int empleado_id = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("ID de la Sala:");
			int sala_id = Intscan.nextInt();
			Intscan.nextLine();

			System.out.println("Fecha de la reserva (yyyy-MM-dd):");
			LocalDate fecha = LocalDate.parse(Strscan.nextLine());

			System.out.println("Hora inicio (HH:mm):");
			LocalTime horaInicio = LocalTime.parse(Strscan.nextLine());

			System.out.println("Hora final (HH:mm):");
			LocalTime horaFinal = LocalTime.parse(Strscan.nextLine());

			Reserva reserva = new Reserva(id, empleado_id, sala_id, fecha, horaInicio, horaFinal);

			if (reservaDAO.conflictoHorarioExceptoId(sala_id, fecha, horaInicio, horaFinal, id)) {
				System.out.println("Error: Conflicto de horario, no se puede actualizar la reserva.");
				return;
			}

			reservaDAO.actualizarReserva(reserva);
			System.out.println("Reserva actualizada");

		} catch (SQLException e) {
			System.out.println("Error al actualizar la reserva: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void eliminarReserva() { // Elimina la reserva si existe
		try {

			verReservas();

			System.out.print("ID de la reserva a eliminar: ");
			int id = Intscan.nextInt();
			Intscan.nextLine();

			reservaDAO.eliminarReserva(id);

		} catch (SQLException e) {
			System.out.println("Error al eliminar la reserva: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
