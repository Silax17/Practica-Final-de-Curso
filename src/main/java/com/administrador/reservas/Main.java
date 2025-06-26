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
			case 1: menuSalas();
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
			case 1: crearSalaTest();
				;
			case 2:
				;
			case 3:
				;
			case 4:
				;
			

			}

		} while (opcion != 0);

	}

	public static void crearSalaTest() {
	    try {
	        Sala sala = new Sala(0, "Sala Prueba", 10, "TV, Pizarra");
	        salaDAO.agregarSala(sala);
	        System.out.println("Sala de prueba insertada.");
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}
	}

