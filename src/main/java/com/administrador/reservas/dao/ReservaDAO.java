package com.administrador.reservas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

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
		} catch (SQLException e) {
			System.out.println("Error al agregar reserva");
			e.printStackTrace();
		}
	}

}
