package com.administrador.reservas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Empleado;

public class EmpleadoDAO {
	// CREATE
	public void agregarEmpleado(Empleado empleado) throws SQLException {
		String sql = "INSERT INTO EMPLEADO(nombre,email,departamento) VALUES(?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, empleado.getNombre());
			st.setString(1, empleado.getEmail());
			st.setString(1, empleado.getDepartamento());
			st.executeUpdate();

		}catch (SQLException e) {
			System.out.println("No se ha podido agregar empleado");
			e.printStackTrace();
		}

	}

}
