package com.administrador.reservas.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.administrador.reservas.ConexionBD;
import com.administrador.reservas.modelo.Reserva;

public class ReservaDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    private ReservaDAO reservaDAO;

    private MockedStatic<ConexionBD> conexionBDMockStatic;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        reservaDAO = new ReservaDAO();

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        conexionBDMockStatic = mockStatic(ConexionBD.class);
        when(ConexionBD.getConnection()).thenReturn(mockConnection);
    }

    @AfterEach
    public void tearDown() {
        if (conexionBDMockStatic != null) {
            conexionBDMockStatic.close();
        }
    }

    @Test
    public void testAgregarReservaExitoso() throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setEmpleado_id(1);
        reserva.setId(2); // sala_id
        reserva.setFecha(LocalDate.of(2025, 6, 30));
        reserva.setHora_inicio(LocalTime.of(10, 0));
        reserva.setHora_final(LocalTime.of(12, 0)); // asegúrate que es "hora_final" no "hora_fin"

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        reservaDAO.agregarReserva(reserva);

        // 🔥 Asegúrate de que este SQL es idéntico al del DAO
        verify(mockConnection).prepareStatement(
            "INSERT INTO RESERVA(empleado_id,sala_id,fecha,hora_inicio,hora_final) VALUES (?,?,?,?,?)"
        );
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 2);
        verify(mockPreparedStatement).setDate(eq(3), any(java.sql.Date.class));
        verify(mockPreparedStatement).setTime(eq(4), any(java.sql.Time.class));
        verify(mockPreparedStatement).setTime(eq(5), any(java.sql.Time.class));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testEliminarReservaExitoso() throws SQLException {
        int reservaId = 3;

        // Usar el mockPreparedStatement ya existente
        when(mockConnection.prepareStatement("DELETE FROM RESERVA WHERE id = ?"))
            .thenReturn(mockPreparedStatement);

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        reservaDAO.eliminarReserva(reservaId);

        verify(mockPreparedStatement).setInt(1, reservaId);
        verify(mockPreparedStatement).executeUpdate();
    }
}
