package com.administrador.reservas.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Reserva;

public class ReservaDAO {
//CREATE
	public void agregarReserva(Reserva reserva) throws SQLException {
		String sql = "INSERT INTO RESERVA(empleado_id,sala_id,fecha,hora_inicio,hora_final) VALUES (?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, reserva.getEmpleado_id());
			st.setInt(2, reserva.getSala_id());
			st.setDate(3, Date.valueOf(reserva.getFecha()));
			st.setTime(4, Time.valueOf(reserva.getHora_inicio()));
			st.setTime(5, Time.valueOf(reserva.getHora_final()));
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al agregar reserva");
			e.printStackTrace();
		}
	}

//READ
	public List<Reserva> verReserva() throws SQLException {
		List<Reserva> reservaSQL = new ArrayList<>();
		String sql = "SELECT * FROM RESERVA";
		try (Connection con = ConexionBD.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(rs.getInt("id"));
				reserva.setEmpleado_id(rs.getInt("empleado_id"));
				reserva.setSala_id(rs.getInt("sala_id"));
				reserva.setFecha(rs.getDate("fecha").toLocalDate());
				reserva.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				reserva.setHora_final(rs.getTime("hora_final").toLocalTime());
				reservaSQL.add(reserva);
			}
		} catch (SQLException e) {
			System.out.println("Error al ver reserva");
			e.printStackTrace();
		}
		return reservaSQL;

	}

//UPDATE
	public void actualizarReserva(Reserva reserva) throws SQLException {
		String sql = "UPDATE RESERVA SET empleado_id=?,sala_id=?,fecha=?,hora_inicio=?,hora_final=? WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, reserva.getEmpleado_id());
			st.setInt(2, reserva.getSala_id());
			st.setDate(3, Date.valueOf(reserva.getFecha()));
			st.setTime(4, Time.valueOf(reserva.getHora_inicio()));
			st.setTime(5, Time.valueOf(reserva.getHora_final()));
			st.setInt(6, reserva.getId());
		} catch (SQLException e) {
			System.out.println("Error al actualizar reserva");
			e.printStackTrace();
		}
	}

//DELETE
	public void eliminarReserva(int res_id) throws SQLException {
		String sql = "DELETE FROM RESERVA WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, res_id);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar reserva");
			e.printStackTrace();
		}
	}
//CONFLICTO HORAS
	public boolean conflictoHorario(int sala_id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM RESERVA WHERE sala_id = ? AND fecha = ? " +
	                 "AND (hora_inicio < ? AND hora_final > ?)";
	    try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	        st.setInt(1, sala_id);
	        st.setDate(2, Date.valueOf(fecha));
	        st.setTime(3, Time.valueOf(horaFinal));  
	        st.setTime(4, Time.valueOf(horaInicio)); 

	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
}
