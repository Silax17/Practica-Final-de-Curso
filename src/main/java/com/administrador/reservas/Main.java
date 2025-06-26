package com.administrador.reservas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.administrador.reservas.dao.EmpleadoDAO;
import com.administrador.reservas.dao.ReservaDAO;
import com.administrador.reservas.dao.SalaDAO;
import com.administrador.reservas.modelo.Sala;

public class Main {

	private static final Scanner Strscan = new Scanner(System.in);
	private static final Scanner Intscan = new Scanner(System.in);
	private static final SalaDAO salaDAO = new SalaDAO();
	private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
	private static final ReservaDAO reservaDAO = new ReservaDAO();

	public static void main(String args[]) throws SQLException {
		int opcion;
		do {
			System.out.println("\nMenu");
			System.out.println("1.Menu Sala");
			System.out.println("2.Menu Empleado");
			System.out.println("3.Menu Reserva");
			System.out.println("0.Salir");
			opcion = Intscan.nextInt();

			switch (opcion) {
			case 1:
				menuSalas();
				;
			case 2:
				;
			case 3:
				;
			case 0:
				;

			}

		} while (opcion != 0);
	}

	public static void menuSalas() {
		int opcion;
		do {
			System.out.println("\nGestionar Salas");
			System.out.println("1.Crear Sala");
			System.out.println("2.Ver Salas");
			System.out.println("3.Actualizar Salas");
			System.out.println("4.Borrar Sala");
			System.out.println("0. Salir");
			opcion = Intscan.nextInt();

			switch (opcion) {
			case 1:
				crearSala();
				;
			case 2:
				verSalas();
				;
			case 3:actualizarSala();
				;
			case 4:
				;

			}

		} while (opcion != 0);

	}

	public static void crearSala() {
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
		}
	}

	public static void verSalas() {
		try {
			for (Sala sala : salaDAO.verSalas()) {
				System.out.println(sala);

			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void actualizarSala() {
		try {
			System.out.println("Actualizar Sala con ID: ");
			int id = Intscan.nextInt();
			System.out.println("Nombre que desea: ");
			String nombre = Strscan.nextLine();
			System.out.println("Capacidad que desea: ");
			int capacidad = Intscan.nextInt();
			System.out.println("Recursos que desea: ");
			String recursos = Strscan.nextLine();
			System.out.println("Se va actualizar la sala");
			Sala sala = new Sala(id, nombre, capacidad, recursos);
			salaDAO.actualizarSala(sala);
			System.out.println("Sala Actualizada");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
