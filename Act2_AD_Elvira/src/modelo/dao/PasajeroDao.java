package modelo.dao;

import java.util.List;

import modelo.entidad.Pasajero;

public interface PasajeroDao {
	
	/*
	 * Se pide añadir la siguiente funcionalidad.

	Los coches, tendrán asociados N pasajeros en él (habrá que crear la tabla pasajeros y hacer la relación pertinente). Los pasajeros tendrán los siguientes atributos, id, nombre, edad y peso. Se añadirá la opción “gestión de pasajeros” al programa principal, dicha opción nos mostrará un submenú como el que sigue

    Crear nuevo pasajero
    Borrar pasajero por id
    Consulta pasajero por id
    Listar todos los pasajeros
    Añadir pasajero a coche, el programa nos pedirá un id de un pasajero y el id de un coche, y lo añadirá al coche a nivel de base de datos. Sería una buena opción mostrar todos los coches disponibles.
    Eliminar pasajero de un coche, el programa nos pedirá un id de un pasajero y lo eliminará del coche a nivel de base de datos. Sería una buena opción mostrar todos los coches y sus pasajeros asociados.
    Listar todos los pasajeros de un coche, el programa pedirá el id de un coche, y nos mostrará todos los pasajeros asociados a él.

	 */
	
	int altaPasajero(Pasajero p);
	int eliminarPasajero(int id);
	Pasajero buscarPasajero (int id);
	List<Pasajero> listaPasajeros();
	
}
