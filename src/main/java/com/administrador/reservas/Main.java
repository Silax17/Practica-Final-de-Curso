package com.administrador.reservas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.administrador.reservas.dao.EmpleadoDAO;
import com.administrador.reservas.dao.ReservaDAO;
import com.administrador.reservas.dao.SalaDAO;

public class Main {

	private static final Scanner scan = new Scanner(System.in);
	private static final SalaDAO salaDAO = new SalaDAO();
	private static final EmpleadoDAO empleadoDAO= new EmpleadoDAO();
	private static final ReservaDAO reservaDAO=new ReservaDAO();

	public static void main(String args[]) throws SQLException {
		int opcion;
		do {
			System.out.println("\nMenu");
			System.out.println("1.Menu Sala");
			System.out.println("2.Menu Empleado");
			System.out.println("3.Menu Reserva");
			System.out.println("0. Salir");
			opcion= scan.nextInt();
			
			switch (opcion) {
			case 1 :;
			case 2 :;
			case 3 :;
			case 0 :;
			default:System.out.println("Opcion no Valida");

			
			}
			
		}while(opcion !=0);
	}
}
