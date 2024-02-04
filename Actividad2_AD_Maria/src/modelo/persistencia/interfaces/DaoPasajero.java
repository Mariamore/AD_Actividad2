package modelo.persistencia.interfaces;

import java.util.ArrayList;


import modelo.entidad.Pasajero;

public interface DaoPasajero {
	Boolean altaPasajero(Pasajero pasajero);
	Boolean borrarPasajero(int id);
	Pasajero consultarPasajero(int id);
	ArrayList<Pasajero> listarPasajeros();
	Boolean addPasajeroCoche(int idPasajero, int idCoche);
	Boolean eliminarPasajeroCoche(int idPasajero);
	ArrayList<Pasajero> listarPasajerosCoche(int idCoche);
}
