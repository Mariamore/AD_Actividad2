package modelo.dao;

import java.util.List;

import modelo.entidad.CochesConPasajeros;
import modelo.entidad.Pasajero;

public interface CochesConPasajerosDao {
	
	int asignarPasajeroCoche(int id, int idPa);
	int EliminarPasajeroDeCoche(int idPa);

	List<CochesConPasajeros> listaCochesPasajeros();
	
	List<Pasajero> pasajerosDelCoche(int id);
	

}
