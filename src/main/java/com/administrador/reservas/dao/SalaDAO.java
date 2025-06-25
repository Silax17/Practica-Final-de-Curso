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

public class SalaDAO { //CRUD
	//CREATE
	public void agregarSala(Sala sala) throws SQLException{
		String sql ="INSERT INTO SALA (nombre,capacidad,recursos_disponibles) VALUES(?,?,?)";
		try(Connection con=ConexionBD.getConnection(); PreparedStatement st=con.prepareStatement(sql)){
			st.setString(1,sala.getNombre());
			st.setInt(2,sala.getCapacidad());
			st.setString(3,sala.getRecursos_disponibles());
			st.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error al insertar Sala");
			
		}
		
		
	}
	//READ
	public List<Sala> verSalas() throws SQLException{
		List<Sala> salaSQL=new ArrayList<>();
		String sql="SELECT * FROM SALA";
		try(Connection con=ConexionBD.getConnection(); Statement st=con.createStatement();ResultSet rs=st.executeQuery(sql)){
			
			while(rs.next()) {
				Sala sala= new Sala();
				sala.setId(rs.getInt("id"));
				sala.setNombre(rs.getString("nombre"));
				sala.setCapacidad(rs.getInt("Capacidad"));
				sala.setRecursos_disponibles(rs.getString("Recursos_disponibles"));
				salaSQL.add(sala);
			}
			
			
		}catch(SQLException e) {
			System.out.println("Error al ver salas");
			
		}
		return salaSQL;
		
	}
	//UPDATE
	public void actualizarSala(Sala sala) throws SQLException {
		String sql="UPDATE SALA SET nombre=?, capacidad=?,recursos_disponibles=? WHERE ID=?";
		try(Connection con=ConexionBD.getConnection(); PreparedStatement st=con.prepareStatement(sql)){
			st.setString(1, sala.getNombre());
			st.setInt(2, sala.getCapacidad());
			st.setString(3, sala.getRecursos_disponibles());
			st.setInt(4,sala.getId());
			st.executeUpdate()
		}
	}
}
