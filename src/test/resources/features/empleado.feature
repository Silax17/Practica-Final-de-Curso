Feature: Crear Empleado
	Como administrador
	Quiero dar de alta a Empleados
	Para asignarles reservas
	
	
	Scenario: Crear empleados con datos validos
		Given que tengo los datos de un empleado con nombre "Aleix Terés", email "ateres@minsait.com" y departamentos "Ventas"
		When creo el empleado en la Base de Datos
		Then el empleado se guarda correctamente en la base de datos