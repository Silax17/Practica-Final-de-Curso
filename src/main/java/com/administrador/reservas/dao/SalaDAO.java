package com.administrador.reservas.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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
		List<Sala> salasSQL=new ArrayList<>();
		String sql="SELECT * FROM SALA";
		try(Connection con=ConexionBD.getConnection(); Statement st=con.createStatement()){
			
			
		}
		
	}
}
