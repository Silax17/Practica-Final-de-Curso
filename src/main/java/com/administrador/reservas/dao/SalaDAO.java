package com.administrador.reservas.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.administrador.reservas.modelo.Sala;
import com.administrador.reservas.ConexionBD;

public class SalaDAO { // CRUD
	// CREATE
	public void agregarSala(Sala sala) throws SQLException {
		String sql = "INSERT INTO SALA (nombre, capacidad, recursos_disponibles) VALUES (?, ?, ?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, sala.getNombre());
			st.setInt(2, sala.getCapacidad());
			st.setString(3, sala.getRecursos_disponibles());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar Sala: " + e.getMessage());
		}

	}

	// READ
	public List<Sala> verSalas() throws SQLException {
		List<Sala> salaSQL = new ArrayList<>();
		String sql = "SELECT * FROM SALA";
		try (Connection con = ConexionBD.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Sala sala = new Sala();
				sala.setId(rs.getInt("id"));
				sala.setNombre(rs.getString("nombre"));
				sala.setCapacidad(rs.getInt("capacidad"));
				sala.setRecursos_disponibles(rs.getString("recursos_disponibles"));
				salaSQL.add(sala);
			}

		} catch (SQLException e) {
			System.out.println("Error al ver salas");
			e.printStackTrace();

		}
		return salaSQL;

	}
	public Sala verSalaPorId(int id) throws SQLException {
	    Sala sala = null;
	    String sql = "SELECT * FROM SALA WHERE id = ?";
	    try (Connection con = ConexionBD.getConnection();
	         PreparedStatement st = con.prepareStatement(sql)) {
	        st.setInt(1, id);
	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                sala = new Sala();
	                sala.setId(rs.getInt("id"));
	                sala.setNombre(rs.getString("nombre"));
	                sala.setCapacidad(rs.getInt("capacidad"));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener la sala por ID");
	        e.printStackTrace();
	    }
	    return sala;
	}

	// UPDATE
	public void actualizarSala(Sala sala) throws SQLException {
		String sql = "UPDATE SALA SET nombre=?, capacidad=?,recursos_disponibles=? WHERE ID=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, sala.getNombre());
			st.setInt(2, sala.getCapacidad());
			st.setString(3, sala.getRecursos_disponibles());
			st.setInt(4, sala.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar salas");
			e.printStackTrace();
		}
	}

	// DELETE 1 solo sala
	/*public void eliminarSala(int idSala) throws SQLException {
		String sql = "DELETE FROM SALA WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, idSala);
			st.executeUpdate();
			int filas = st.executeUpdate();
			if (filas > 0) {
				System.out.println("Sala eliminada con éxito.");
			} else {
				System.out.println("No se encontró una sala con ese ID.");
			}

		} catch (SQLException e) {
			System.out.println("Error al eliminar sala");
			e.printStackTrace();
		}

	}*/
	//DELETE 2 Al eliminar Sala tienes que elimina Reserva (Foreign key)
	public void eliminarSalaRes(int id) throws SQLException {
	    try (Connection con = ConexionBD.getConnection()) {
	        
	        String sql1 = "DELETE FROM RESERVA WHERE sala_id = ?";
	        try (PreparedStatement st1 = con.prepareStatement(sql1)) {
	            st1.setInt(1, id);
	            st1.executeUpdate();
	        }

	        
	        String sql2 = "DELETE FROM SALA WHERE id = ?";
	        try (PreparedStatement st2 = con.prepareStatement(sql2)) {
	            st2.setInt(1, id);
	            st2.executeUpdate();
	        }
	    }
	}
}
