package com.administrador.reservas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://127.0.0.1:3307/pingu";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, username, password);

	}

}
