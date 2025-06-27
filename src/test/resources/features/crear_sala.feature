Feature: Crear Sala
	Como administrador
	Quiero dar de alta nuevas salas
	Para que puedan reservarse
	
	
Scenario: Crear una sala con datos validos
	Given que tengo los datos de una sala con nombre "Sala A" y capacidad 20
	When creo la sala en la base de datos
	Then la sala se guarda correctamente en la base de datos
	