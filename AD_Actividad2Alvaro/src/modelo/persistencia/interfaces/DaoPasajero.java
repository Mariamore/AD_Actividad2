package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;

public interface DaoPasajero {
	
	/**
	 * Método que da de alta un pasajero en la base de datos. El id será 
	 * generado automáticamente por la base de datos.
	 * @param pasajero El pasajero a dar de alta en la base de datos.
	 * @return True cuando se ha dado de alta correctamente en la base de datos. 
	 * False cuando no se ha podido dar de alta el pasajero. 
	 */
	boolean altaPasajero(Pasajero pasajero);
	
	/**
	 * Método que elimina un pasajero de la base de datos a partir de su id.
	 * @param idPasajero El identificador unívoco del pasajero a dar de baja de la base de datos.
	 * @return True cuando se ha eliminado correctamente de la base de datos. 
	 * False cuando no se ha podido eliminar el pasajero.
	 */
	boolean bajaPasajero(int idPasajero);
	
	/**
	 * Método que devuelve los datos de un pasajero que existen en la base de datos 
	 * a partir de su id.
	 * @param idPasajero El identificador unívoco del pasajero buscado.
	 * @return Pasajero solicitado. Null si no se ha encontrado el pasajero solicitado.
	 */
	Pasajero buscarPasajero(int idPasajero);
	
	/**
	 * Método que devuelve un listado de todos los objetos Pasajero existentes en 
	 * la base de datos.
	 * @return ArrayList de objetos de clase Pasajero. Null si no se ha podido realizar la consulta de datos.
	 */
	List<Pasajero> listarPasajeros();
	
	/**
	 * Método que añade un pasajero a un coche en la base de datos.
	 * @param idPasajero El identificador unívoco del pasajero a añadir al coche.
	 * @param idCoche El identificador unívoco del coche al que se le va a añadir el pasajero.
	 * @return True si se ha podido añadir correctamente el pasajero al coche. 
	 * False si no se ha podido añadir el pasajero al coche.
	 */
	boolean addPasajeroACoche(int idPasajero, int idCoche);
	
	/**
	 * Método que elimina a un pasajero de un coche en la base de datos.
	 * @param idPasajero El identificador unívoco del pasajero a eliminar del coche.
	 * @return True si se ha podido eliminar correctamente el pasajero del coche. 
	 * False si no se ha podido eliminar el pasajero del coche.
	 */
	boolean eliminarPasajeroDeCoche(int idPasajero);
	
	/**
	 * Método que muestra todos los pasajeros que hay asociados a un coche en la base de datos.
	 * @param idCoche El identificador unívoco del coche del que queremos obtener sus pasajeros.
	 * @return ArrayList de objetos de clase Pasajero. Null si no se ha podido realizar la consulta de datos.
	 */
	List<Pasajero> listarPasajerosDeCoche(int idCoche);

}
