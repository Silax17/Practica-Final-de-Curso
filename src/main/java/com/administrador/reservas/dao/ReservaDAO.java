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
	/**
	 * Clase DAO encargada de manejar las operaciones CRUD y validaciones realacionadas con las horas
	 */
//CREATE - Agrega una nueva reserva
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

//READ - Obtiene todas las reservas
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

//UPDATE - Actualiza una reserva existente
	public void actualizarReserva(Reserva reserva) throws SQLException {
		String sql = "UPDATE RESERVA SET empleado_id=?,sala_id=?,fecha=?,hora_inicio=?,hora_final=? WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, reserva.getEmpleado_id());
			st.setInt(2, reserva.getSala_id());
			st.setDate(3, Date.valueOf(reserva.getFecha()));
			st.setTime(4, Time.valueOf(reserva.getHora_inicio()));
			st.setTime(5, Time.valueOf(reserva.getHora_final()));
			st.setInt(6, reserva.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar reserva");
			e.printStackTrace();
		}
	}

//DELETE - Eliminar una reserva por su ID 
	public void eliminarReserva(int res_id) throws SQLException {
		String sql = "DELETE FROM RESERVA WHERE id=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, res_id);
			int filas = st.executeUpdate();
			if (filas > 0) {
				System.out.println("Reserva eliminada correctamente.");
			} else {
				System.out.println("No se encontró ninguna reserva con ese ID.");
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar reserva");
			e.printStackTrace();
		}
	}

// Validacion CONFLICTO HORAS
	public boolean conflictoHorario(int sala_id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal)
			throws SQLException {
		String sql = "SELECT COUNT(*) FROM RESERVA WHERE sala_id = ? AND fecha = ? "
				+ "AND (hora_inicio < ? AND hora_final > ?)";
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
// Validacion - Verifica conflicto de horario ignorando una reserva especifica (Steps)
	public boolean conflictoHorarioExceptoId(int salaId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal,
			int idReservaIgnorar) throws SQLException {
		String sql = "SELECT COUNT(*) FROM RESERVA WHERE sala_id = ? AND fecha = ? "
				+ "AND (hora_inicio < ? AND hora_final > ?) AND id <> ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, salaId);
			st.setDate(2, Date.valueOf(fecha));
			st.setTime(3, Time.valueOf(horaFinal));
			st.setTime(4, Time.valueOf(horaInicio));
			st.setInt(5, idReservaIgnorar);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

//COMPROBAR reserva por ID
	public Reserva obtenerReservaPorId(int id) throws SQLException {
		String sql = "SELECT * FROM RESERVA WHERE id = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return new Reserva(rs.getInt("id"), rs.getInt("empleado_id"), rs.getInt("sala_id"),
							rs.getDate("fecha").toLocalDate(), rs.getTime("hora_inicio").toLocalTime(),
							rs.getTime("hora_final").toLocalTime());
				} else {
					return null;
				}
			}
		}
	}
}
