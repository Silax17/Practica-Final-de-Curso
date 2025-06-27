package com.administrador.reservas.dao;

import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Empleado;

public class EmpleadoDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    private EmpleadoDAO empleadoDAO;
    
    private MockedStatic<ConexionBD> conexionBDMockStatic;


    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        empleadoDAO = new EmpleadoDAO();

        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

       
        conexionBDMockStatic = mockStatic(ConexionBD.class);
        conexionBDMockStatic.when(ConexionBD::getConnection).thenReturn(mockConnection);
    }

    @AfterEach
    public void tearDown() {
        
        if (conexionBDMockStatic != null) {
            conexionBDMockStatic.close();
        }
    }
    @Test
    public void testAgregarEmpleadoExitoso() throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setNombre("Aleix Terés");
        empleado.setEmail("ateres@minsait.com");
        empleado.setDepartamento("Ventas");

       
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

       
        empleadoDAO.agregarEmpleado(empleado);

        
        verify(mockConnection).prepareStatement("INSERT INTO EMPLEADO(nombre,email,departamento) VALUES(?,?,?)");

        
        verify(mockPreparedStatement).setString(1, "Aleix Terés");
        verify(mockPreparedStatement).setString(2, "ateres@minsait.com");
        verify(mockPreparedStatement).setString(3, "Ventas");

        
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testEliminarEmpleadoResExitoso() throws SQLException {
        int empleadoId = 3;

        
        PreparedStatement mockPreparedStatementReservas = mock(PreparedStatement.class);
        PreparedStatement mockPreparedStatementEmpleado = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement("DELETE FROM RESERVA WHERE empleado_id = ?")).thenReturn(mockPreparedStatementReservas);
        when(mockConnection.prepareStatement("DELETE FROM EMPLEADO WHERE id = ?")).thenReturn(mockPreparedStatementEmpleado);

        when(mockPreparedStatementReservas.executeUpdate()).thenReturn(1);
        when(mockPreparedStatementEmpleado.executeUpdate()).thenReturn(1);

        
        empleadoDAO.eliminarEmpleadoRes(empleadoId);

        
        verify(mockPreparedStatementReservas).setInt(1, empleadoId);
        verify(mockPreparedStatementReservas).executeUpdate();

        
        verify(mockPreparedStatementEmpleado).setInt(1, empleadoId);
        verify(mockPreparedStatementEmpleado).executeUpdate();
    }
}
