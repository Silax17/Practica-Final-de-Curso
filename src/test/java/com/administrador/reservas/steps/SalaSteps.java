package com.administrador.reservas.steps;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import io.cucumber.java.en.*;
import com.administrador.reservas.dao.SalaDAO;
import com.administrador.reservas.modelo.Sala;

public class SalaSteps {

    private SalaDAO salaDAO = new SalaDAO();
    private Sala sala;
    private boolean resultadoOperacion;

    @Given("que tengo los datos de una sala con nombre {string} y capacidad {int}")
    public void tengo_los_datos_de_una_sala(String nombre, int capacidad) {
        sala = new Sala();
        sala.setNombre(nombre);
        sala.setCapacidad(capacidad);
    }

    @When("creo la sala en la base de datos")
    public void creo_la_sala_en_la_base_de_datos() {
        try {
            salaDAO.agregarSala(sala);
            resultadoOperacion = true;
        } catch (Exception e) {
            resultadoOperacion = false;
        }
    }

    @Then("la sala se guarda correctamente en la base de datos")
    public void la_sala_se_guarda_correctamente_en_la_base_de_datos() {
        assertTrue(resultadoOperacion);
    }

    @Given("que existe una sala con ID {int} en el sistema")
    public void existe_una_sala_con_id(int id) throws SQLException {
        sala = salaDAO.verSalaPorId(id);
        assertNotNull(sala);
    }

    @When("elimino la sala con ID {int}")
    public void elimino_la_sala_con_id(int id) {
        try {
            salaDAO.eliminarSalaRes(id);
            resultadoOperacion = true;
        } catch (Exception e) {
            resultadoOperacion = false;
        }
    }

    @Then("la sala y sus reservas asociadas se eliminan correctamente")
    public void la_sala_y_sus_reservas_se_eliminan_correctamente() {
        assertTrue(resultadoOperacion);
    }
}
