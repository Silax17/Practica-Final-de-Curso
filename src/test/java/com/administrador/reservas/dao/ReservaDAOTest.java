package com.administrador.reservas.dao;

import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import com.administrador.reservas.ConexionBD;

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
    public void testEliminarReservaExitoso() throws SQLException {
        int reservaId = 5;

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        reservaDAO.eliminarReserva(reservaId);

        verify(mockConnection).prepareStatement("DELETE FROM RESERVA WHERE id=?");
        verify(mockPreparedStatement).setInt(1, reservaId);
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }
}
