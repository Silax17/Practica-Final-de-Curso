package com.administrador.reservas.steps;

import static org.junit.jupiter.api.Assertions.*;
import com.administrador.reservas.dao.ReservaDAO;
import com.administrador.reservas.modelo.Reserva;

import io.cucumber.java.en.*;

import java.sql.SQLException;

public class ReservaSteps {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private Reserva reservaObtenida;
    private boolean eliminado;
    private int reservaId;

    // --- Step para obtener reserva por id ---
    @Given("existe una reserva con ID {int}")
    public void existeUnaReservaConID(int id) throws SQLException {
        reservaId = id;
        reservaObtenida = reservaDAO.obtenerReservaPorId(id);
       
        assertNotNull(reservaObtenida, "La reserva con ID " + id + " no existe");
    }

    @When("obtengo la reserva con ID {int}")
    public void obtengoLaReservaConID(int id) throws SQLException {
        reservaObtenida = reservaDAO.obtenerReservaPorId(id);
    }

    @Then("la reserva obtenida debe tener ID {int}")
    public void laReservaObtenidaDebeTenerID(int id) {
        assertNotNull(reservaObtenida, "No se encontró la reserva");
        assertEquals(id, reservaObtenida.getId());
    }

    
    @Given("que quiero eliminar la reserva con ID {int}")
    public void queQuieroEliminarLaReservaConID(int id) {
        this.reservaId = id;
    }

    @When("elimino la reserva con ID {int}")
    public void eliminoLaReservaConID(int id) throws SQLException {
        reservaDAO.eliminarReserva(id);
        
        eliminado = true;
    }

    @Then("la reserva con ID {int} ya no debe existir")
    public void laReservaConIDYaNoDebeExistir(int id) throws SQLException {
        Reserva r = reservaDAO.obtenerReservaPorId(id);
        assertNull(r, "La reserva con ID " + id + " aún existe");
        assertTrue(eliminado);
    }
}
