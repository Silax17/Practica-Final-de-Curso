Feature: Eliminar Reserva
	Como administrador
	Quiero eliminar Reservas
	Para mantener actualizada l abase de datos
	
Scenario: Eliminar una reserva existente
	Given que existe una reserva con ID 3 en la base de datos
	When elimino la reserva con ID 3
	Then la reserva se elimina correctamente de la base de datos
	