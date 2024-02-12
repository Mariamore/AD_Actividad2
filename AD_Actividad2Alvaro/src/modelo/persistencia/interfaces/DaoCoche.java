package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {
	
	/**
	 * Método que da de alta un coche en la base de datos. El id será 
	 * generado automáticamente por la base de datos.
	 * @param coche El coche a dar de alta en la base de datos.
	 * @return True cuando se ha dado de alta correctamente en la base de datos. 
	 * False cuando no se ha podido dar de alta el coche. 
	 */
	boolean altaCoche(Coche coche);
	
	/**
	 * Método que elimina un coche de la base de datos a partir de su id.
	 * @param idCoche El identificador unívoco del coche a dar de baja de la base de datos.
	 * @return True cuando se ha eliminado correctamente de la base de datos. 
	 * False cuando no se ha podido eliminar el coche.
	 */
	boolean bajaCoche(int idCoche);
	
	/**
	 * Método que devuelve los datos de un coche que existen en la base de datos 
	 * a partir de su id.
	 * @param idCoche El identificador unívoco del coche buscado.
	 * @return Coche solicitado. Null si no se ha encontrado el coche solicitado.
	 */
	Coche buscarCoche(int idCoche);
	
	/**
	 * Método que modifica los datos de un coche ya existente en la base de datos 
	 * a partir de su id.
	 * @param coche Objeto de la clase Coche que contiene el id del coche a modificar 
	 * y los datos a sustituir en la base de datos.
	 * @return True si la modificación se ha realizado correctamente. 
	 * False si no se ha podido realizar la modificación de datos.
	 */
	boolean modificarCoche(Coche coche);
	
	/**
	 * Método que devuelve un listado de todos los objetos Coche existentes en 
	 * la base de datos.
	 * @return ArrayList de objetos de clase Coche. Null si no se ha podido realizar la consulta de datos.
	 */
	List<Coche> listarCoches();

	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos
	 * que tengan asignado algún pasajero
	 * @return el arraylist con los coches con pasajeros asignados, guardados en la base de datos, 
	 * el arraylist vacío,en caso de que no existan coches almacenados en la base de datos que tengan asignados
	 * pasajeros, null en caso de error
	 */
	List<Coche> cochesConPasajeros();

	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos que tengan plazas 
	 * disponibles 
	 * @return el arraylist con los coches con plazas libres, guardados en la base de datos, 
	 * el arraylist vacío,en caso de que no existan coches almacenados en la base de datos que tengan plazas libres,
	 * null en caso de error
	 */
	List<Coche> cochesDisponibles();

}
