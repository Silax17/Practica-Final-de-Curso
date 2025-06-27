Feature: Crear reserva
	Como administrador
	Quiero crear reservas
	Para gestionar el uso de salas
	
Scenario: Crear reserva con datos invalidos
	Given que tengo los datos de una reserva con empleadoId 1, salaId 2, fecha "2025-06-30", hora inicial "10:00" y hora final "12:00"
	When creo la reserva en la base de datps
	Then la reserva se guarda correctamente en la base de datos
	