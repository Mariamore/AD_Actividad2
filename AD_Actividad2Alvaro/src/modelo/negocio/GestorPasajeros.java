package modelo.negocio;

import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMySql;

public class GestorPasajeros {
	
	private DaoPasajeroMySql daoPasajero = new DaoPasajeroMySql();
	
	/**
	 * Método que envía un pasajero para ser dado de alta en la base de datos.
	 * @param pasajero El pasajero que se enviará para dar de alta a la base de datos.
	 * @return True si el pasajero se ha podido dar de alta correctamente. 
	 * False si no se ha podido dar de alta.
	 */
	public boolean alta(Pasajero pasajero) {
		boolean alta = false;
		//Como regla de negocio, no puede estar vacío el nombre del pasajero.
		//El método isBlank() asegura que ni esté vacío ni tenga solamente espacios en blanco.
		if(!pasajero.getNombre().isBlank()) {
			alta = daoPasajero.altaPasajero(pasajero);
		}
		return alta;
	}
	
	/**
	 * Método que envía el id de un pasajero para ser dado de baja en la base de datos.
	 * @param idPasajero El identificador unívoco del pasajero que se dará de baja en la base de datos.
	 * @return True si el pasajero se ha dado de baja correctamente de la base de datos. 
	 * False si no se ha podido dar de baja.
	 */
	public boolean baja(int idPasajero) {
		boolean baja = daoPasajero.bajaPasajero(idPasajero);
		return baja;
	}
	
	/**
	 * Método que envía un pasajero para ser buscado en la base de datos a partir de su id.
	 * @param idPasajero El identificador unívoco del pasajero que será enviado a la base de datos 
	 * para obtener la información almacenada.
	 * @return Objeto de la clase Pasajero con la información que existe en la base de datos con el id 
	 * proporcionado.
	 */
	public Pasajero buscar(int idPasajero) {
		Pasajero pasajero = daoPasajero.buscarPasajero(idPasajero);
		return pasajero;
	}
	
	/**
	 * Método que solicita toda la información existente de los pasajeros de la base de datos.
	 * @return Objeto de la clase ArrayList con todos los pasajeros de la base de datos. 
	 * Null si no se ha podido realizar la búsqueda.
	 */
	public List<Pasajero> listar(){
		List<Pasajero> listaPasajeros = daoPasajero.listarPasajeros();
		return listaPasajeros;
	}
	
	/**
	 * Método que envía un identificador de pasajero y un identificador de coche para 
	 * poder añadir el ID del coche en los datos del pasajero en la base de datos.
	 * @param idPasajero Identificador unívoco del pasajero al que se le va a incorporar el ID de un coche.
	 * @param idCoche Identificador unívoco del coche a incluir en los datos del pasajero en la base de datos.
	 * @return True si se ha podido añadir el ID del coche en el pasajero correctamente. 
	 * False si no se ha podido llevar a cabo la operación.
	 */
	public boolean pasajeroACoche(int idPasajero, int idCoche) {
		boolean agregado = daoPasajero.addPasajeroACoche(idPasajero, idCoche);
		return agregado;
	}
	
	/**
	 * Método que envía un identificador de pasajero para poder eliminar el ID del coche 
	 * en los datos del pasajero en la base de datos.
	 * @param idPasajero Identificador unívoco del pasajero al que se le va a eliminar el ID de un coche.
	 * @return True si se ha podido eliminar el ID del coche en el pasajero correctamente. 
	 * False si no se ha podido llevar a cabo la operación.
	 */
	public boolean pasajeroEliminarCoche(int idPasajero) {
		boolean eliminado = daoPasajero.eliminarPasajeroDeCoche(idPasajero);
		return eliminado;
	}
	
	/**
	 * Método que solicita toda la información existente de los pasajeros de la base de datos.
	 * @return Objeto de la clase ArrayList con todos los pasajeros de la base de datos. 
	 * Null si no se ha podido realizar la búsqueda.
	 */
	public List<Pasajero> listarPasajerosDeCoche(int idCoche){
		List<Pasajero> listaPasajeros = daoPasajero.listarPasajerosDeCoche(idCoche);
		return listaPasajeros;
	}

}
