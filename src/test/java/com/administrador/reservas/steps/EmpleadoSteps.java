package com.administrador.reservas.steps;

import com.administrador.reservas.dao.EmpleadoDAO;
import com.administrador.reservas.modelo.Empleado;

import io.cucumber.java.es.*;

public class EmpleadoSteps {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private Empleado empleado;
    private Exception lastException;

    @Dado("que tengo los datos de un empleado con nombre {string}, email {string} y departamentos {string}")
    public void datosEmpleado(String nombre, String email, String departamento) {
        empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setEmail(email);
        empleado.setDepartamento(departamento);
    }

    @Cuando("creo el empleado en la Base de Datos")
    public void crearEmpleado() {
        try {
            empleadoDAO.agregarEmpleado(empleado);
            lastException = null;
        } catch (Exception e) {
            lastException = e;
        }
    }

    @Entonces("el empleado se guarda correctamente en la base de datos")
    public void empleadoGuardadoCorrectamente() {
        if (lastException != null) {
            throw new AssertionError("No se pudo guardar el empleado: " + lastException.getMessage());
        }
        
    }

    @Dado("que existe un empleado con ID {int} en el sistema")
    public void existeEmpleadoConID(int id) {
        try {
            empleado = empleadoDAO.obtenerEmpleadoPorId(id);
            if (empleado == null) {
                throw new AssertionError("No existe empleado con ID " + id);
            }
            lastException = null;
        } catch (Exception e) {
            lastException = e;
            throw new AssertionError("Error al buscar empleado con ID " + id + ": " + e.getMessage());
        }
    }

    @Cuando("elimino el empleado con ID {int}")
    public void eliminarEmpleadoPorID(int id) {
        try {
            empleadoDAO.eliminarEmpleadoRes(id);
            lastException = null;
        } catch (Exception e) {
            lastException = e;
        }
    }

    @Entonces("el empleado y sus reservas asociadas se eliminan correctamente")
    public void empleadoEliminadoCorrectamente() {
        if (lastException != null) {
            throw new AssertionError("No se pudo eliminar el empleado: " + lastException.getMessage());
        }
        
    }
}
