package com.administrador.reservas.steps;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.administrador.reservas.dao.ReservaDAO;
import com.administrador.reservas.modelo.Reserva;

import io.cucumber.java.en.*;

public class ReservaSteps {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private Reserva reserva;
    private boolean resultado;
    private int idReserva;

    @Given("que tengo los datos de una reserva con empleadoId {int}, salaId {int}, fecha {string}, hora inicio {string} y hora fin {string}")
    public void tengo_los_datos_de_una_reserva(int empleadoId, int salaId, String fecha, String horaInicio, String horaFin) {
        reserva = new Reserva();
        reserva.setEmpleado_id(empleadoId);
        reserva.setId(salaId);
        reserva.setFecha(LocalDate.parse(fecha));
        reserva.setHora_inicio(LocalTime.parse(horaInicio));
        reserva.setHora_final(LocalTime.parse(horaFin));
    }
    @When("creo la reserva en la base de datos")
    public void creo_la_reserva_en_la_base_de_datos() {
        try {
            reservaDAO.agregarReserva(reserva);
            resultado = true;
        } catch (SQLException e) {
            resultado = false;
            e.printStackTrace();
        }
    }

    @Then("la reserva se guarda correctamente en la base de datos")
    public void la_reserva_se_guarda_correctamente_en_la_base_de_datos() {
        assertTrue(resultado, "La reserva no se guardó correctamente.");
    }

    @Given("que existe una reserva con ID {int} en la base de datos")
    public void que_existe_una_reserva_con_ID_en_la_base_de_datos(int id) {
       
        idReserva = id;
    }

    @When("elimino la reserva con ID {int}")
    public void elimino_la_reserva_con_ID(int id) {
        try {
            reservaDAO.eliminarReserva(id);
            resultado = true;
        } catch (SQLException e) {
            resultado = false;
            e.printStackTrace();
        }
    }

    @Then("la reserva se elimina correctamente de la base de datos")
    public void la_reserva_se_elimina_correctamente_de_la_base_de_datos() {
        assertTrue(resultado, "La reserva no se eliminó correctamente.");
    }
}
