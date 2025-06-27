package com.administrador.reservas.dao;

import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Empleado;

public class EmpleadoDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    private EmpleadoDAO empleadoDAO;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        empleadoDAO = new EmpleadoDAO();

        // Mock para la conexión y el prepared statement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock para obtener la conexión de la clase ConexionBD
        mockStatic(ConexionBD.class);
        when(ConexionBD.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testAgregarEmpleadoExitoso() throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setNombre("Aleix Terés");
        empleado.setEmail("ateres@minsait.com");
        empleado.setDepartamento("Ventas");

        // Simular que executeUpdate devuelve 1 (una fila afectada)
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Ejecutar el método
        empleadoDAO.agregarEmpleado(empleado);

        // Verificar que prepareStatement fue llamado con el SQL correcto
        verify(mockConnection).prepareStatement("INSERT INTO EMPLEADO(nombre,email,departamento) VALUES(?,?,?)");

        // Verificar que setString se haya llamado 3 veces (nombre, email, departamento)
        verify(mockPreparedStatement).setString(1, "Aleix Terés");
        verify(mockPreparedStatement).setString(2, "ateres@minsait.com");
        verify(mockPreparedStatement).setString(3, "Ventas");

        // Verificar que executeUpdate fue llamado
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testEliminarEmpleadoResExitoso() throws SQLException {
        int empleadoId = 3;

        // Simular prepared statements para DELETE reservas y empleado
        PreparedStatement mockPreparedStatementReservas = mock(PreparedStatement.class);
        PreparedStatement mockPreparedStatementEmpleado = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement("DELETE FROM RESERVA WHERE empleado_id = ?")).thenReturn(mockPreparedStatementReservas);
        when(mockConnection.prepareStatement("DELETE FROM EMPLEADO WHERE id = ?")).thenReturn(mockPreparedStatementEmpleado);

        when(mockPreparedStatementReservas.executeUpdate()).thenReturn(1);
        when(mockPreparedStatementEmpleado.executeUpdate()).thenReturn(1);

        // Ejecutar el método
        empleadoDAO.eliminarEmpleadoRes(empleadoId);

        // Verificar que el preparedStatement para reservas fue configurado correctamente
        verify(mockPreparedStatementReservas).setInt(1, empleadoId);
        verify(mockPreparedStatementReservas).executeUpdate();

        // Verificar que el preparedStatement para empleado fue configurado correctamente
        verify(mockPreparedStatementEmpleado).setInt(1, empleadoId);
        verify(mockPreparedStatementEmpleado).executeUpdate();
    }
}
