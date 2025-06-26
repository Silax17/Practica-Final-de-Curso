package com.administrador.reservas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.administrador.reservas.dao.EmpleadoDAO;
import com.administrador.reservas.dao.ReservaDAO;
import com.administrador.reservas.dao.SalaDAO;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);
	private static final SalaDAO salaDAO = new SalaDAO();
	private static final EmpleadoDAO empleadoDAO= new EmpleadoDAO();
	private static final ReservaDAO reservaDAO=new ReservaDAO();

	public static void main(String args[]) throws SQLException {

	}
}
