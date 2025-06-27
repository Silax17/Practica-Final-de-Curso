package com.administrador.reservas.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Sala;

public class SalaDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;
    
    @Mock
    private ResultSet mockResultSet;

    private SalaDAO salaDAO;

    private MockedStatic<ConexionBD> conexionBDMockStatic;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        salaDAO = new SalaDAO();

        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        
        
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        
        conexionBDMockStatic = mockStatic(ConexionBD.class);
        when(ConexionBD.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testObtenerSalaPorIdExitoso() throws SQLException {
        
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn("Sala A");
        when(mockResultSet.getInt("capacidad")).thenReturn(30);

        Sala sala = salaDAO.verSalaPorId(1);

        assertNotNull(sala);
        assertEquals(1, sala.getId());
        assertEquals("Sala A", sala.getNombre());
        assertEquals(30, sala.getCapacidad());

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    public void testAgregarSalaExitoso() throws SQLException {
        Sala sala = new Sala();
        sala.setNombre("Sala B");
        sala.setCapacidad(50);
        sala.setRecursos_disponibles("Pizarra"); 

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        salaDAO.agregarSala(sala);

        verify(mockConnection).prepareStatement("INSERT INTO SALA (nombre, capacidad, recursos_disponibles) VALUES (?, ?, ?)");
        verify(mockPreparedStatement).setString(1, "Sala B");
        verify(mockPreparedStatement).setInt(2, 50);
        verify(mockPreparedStatement).setString(3, "Pizarra");  
        verify(mockPreparedStatement).executeUpdate();
    }



    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
        if (conexionBDMockStatic != null) {
            conexionBDMockStatic.close();
        }
    }
}
