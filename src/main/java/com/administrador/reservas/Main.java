package com.administrador.reservas;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	public static void main(String args[]) throws SQLException {
		try(Connection con=ConexionBD.getConnection()){
			if(con!=null && !con.isClosed()) {
				System.out.println("Base de dades funcional");
			}
			else {
				System.out.println("No funciona ");
			}
			
		}catch(SQLException e) {
			System.err.println("Error conectando a la base de datos:" + e.getMessage());
		}
			
	}
}
