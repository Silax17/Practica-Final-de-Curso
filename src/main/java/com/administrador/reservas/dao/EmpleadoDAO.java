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

/**
 * Clase DAO que gestiona las operacions CRUD relacionadas con los empleados
 */
public class EmpleadoDAO {
	// CREATE - Agregar un nuevo empleado a la base de datos
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

	// READ - Obtener todos los empleados de la base de datos
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
	// READ - Obtener empleado por ID para los Steps
	public Empleado obtenerEmpleadoPorId(int id) throws SQLException {
	    Empleado empleado = null;
	    String sql = "SELECT * FROM EMPLEADO WHERE id = ?";
	    try (Connection con = ConexionBD.getConnection();
	         PreparedStatement st = con.prepareStatement(sql)) {
	        st.setInt(1, id);
	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                empleado = new Empleado();
	                empleado.setId(rs.getInt("id"));
	                empleado.setNombre(rs.getString("nombre"));
	                empleado.setEmail(rs.getString("email"));
	                empleado.setDepartamento(rs.getString("departamento"));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("No se pudo obtener empleado por ID");
	        e.printStackTrace();
	    }
	    return empleado;
	}

	// UPDATE - Acutalizar información de un empleado existente
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

	// DELETE - Eliminar empleado y sus reservas asociadas
/*	public void eliminarEmpleado(int idEmp) throws SQLException {
		String sql = "DELETE FROM EMPLEADO WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, idEmp);
			int filas = st.executeUpdate();
			if (filas > 0) {
				System.out.println("Empleado eliminado correctamente.");
			} else {
				System.out.println("No se encontró ningún empleado con ese ID.");
			}

		} catch (SQLException e) {
			System.out.println("Error al eliminar empleado");
			e.printStackTrace();
		}

	}*/

// DELETE - Eliminar empleado y sus reservas asociaadas
	public void eliminarEmpleadoRes(int id) throws SQLException {
		try (Connection con = ConexionBD.getConnection()) {
			
			String sql1 = "DELETE FROM RESERVA WHERE empleado_id = ?";
			try (PreparedStatement st1 = con.prepareStatement(sql1)) {
				st1.setInt(1, id);
				st1.executeUpdate();
			}

			
			String sql2 = "DELETE FROM EMPLEADO WHERE id = ?";
			try (PreparedStatement st2 = con.prepareStatement(sql2)) {
				st2.setInt(1, id);
				st2.executeUpdate();
			}
		}
	}

//Validar nombre i email
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
