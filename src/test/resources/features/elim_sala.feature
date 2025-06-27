Feature: Eliminar Sala
	Como administrador
	Quiero Eliminar Sala
	Para mantener actualizada la base de datos
	
Scenario: Eliminar una sala existente
	Given que existe una sala con ID 2 en el sistema
  When elimino la sala con ID 2
  Then la sala y sus reservas asociadas se eliminan correctamente