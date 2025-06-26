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
//READ
	public List<Reserva>  verReserva() throws SQLException {
		List<Reserva> reservaSQL = new ArrayList<>();
		String sql="SELECT * FROM RESERVA";
		try(Connection con = ConexionBD.getConnection(); Statement st=con.createStatement(); ResultSet rs= st.executeQuery(sql)){
			while(rs.next()) {
				Reserva reserva=new Reserva();
				reserva.setId(rs.getInt("id"));
				reserva.setEmpleado_id(rs.getInt("empleado_id"));
				reserva.setSala_id(rs.getInt("sala_id"));
				reserva.setFecha(rs.getDate("fecha").toLocalDate());
				reserva.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				reserva.setHora_final(rs.getTime("hora_inicio").toLocalTime());
			}
		}catch (SQLException e) {
			System.out.println("Error al ver reserva");
			e.printStackTrace();
		}
		return reservaSQL;
		
	}

}
