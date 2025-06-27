Feature: Eliminar Empleado
	Como administrador
	Quiero eliminar Empleados
	Para mantener actualizada la base de datos
	
Scenario: Eliminar un empleado existente
	Given que existe un empleado con ID 3 en el sistema
	When elimino el empleado con ID 3
	Then el empleado y sus reservas asociadas se eliminan correctamente