package com.administrador.reservas.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Empleado;
import com.administrador.reservas.modelo.Sala;

public class EmpleadoDAO {
	// CREATE
	public void agregarEmpleado(Empleado empleado) throws SQLException {
		String sql = "INSERT INTO EMPLEADO(nombre,email,departamento) VALUES(?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, empleado.getNombre());
			st.setString(2, empleado.getEmail());
			st.setString(3, empleado.getDepartamento());
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("No se ha podido agregar empleado");
			e.printStackTrace();
		}

	}

	// READ
	public List<Empleado> verEmpleados() throws SQLException {
		List<Empleado> empleadoSQL = new ArrayList<>();
		String sql = "SELECT * FROM EMPLEADO";
		try (Connection con = ConexionBD.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setId(rs.getInt("id"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setEmail(rs.getString("email"));
				empleado.setDepartamento(rs.getString("departamento"));
				empleadoSQL.add(empleado);
			}

		} catch (SQLException e) {
			System.out.println("No se ha podido ver empleados");
			e.printStackTrace();
		}
		return empleadoSQL;
	}

	// UPDATE
	public void actualizarEmpleados(Empleado empleado) throws SQLException {
		String sql = "UPDATE EMPLEADO SET nombre=?, email=?, departamento=? WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, empleado.getNombre());
			st.setString(2, empleado.getEmail());
			st.setString(3, empleado.getDepartamento());
			st.setInt(4, empleado.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar empleados");
			e.printStackTrace();
		}
	}

	// DELETE
	public void eliminarEmpleado(int idEmp) throws SQLException {
		String sql = "DELETE FROM EMPLEADO WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, idEmp);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al eliminar sala");
			e.printStackTrace();
		}

	}

	public boolean validarEmpleado(Empleado empleado) {
		if (empleado.getNombre() == null || !empleado.getNombre().trim().contains(" ")) {
			System.out.println("El nombre debe incluir nombre y apellido.");
			return false;
		}

		if (empleado.getEmail() == null || !empleado.getEmail().contains("@")) {
			System.out.println("El email debe contener '@'.");
			return false;
		}
		return true;
	}

}
