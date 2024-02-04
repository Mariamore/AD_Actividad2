package modelo.negocio;


import java.util.ArrayList;


import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {
	
	private DaoPasajero daoPasajero = new DaoPasajeroMySql();
	
	/**
	 * Método para dar de alta un objeto de tipo pasajero en la base de datos
	 * @param pasajero el objeto pasajero a dar de alta
	 * @return true si se ha dado de alta, false en caso contrario
	 */
	
	public boolean alta(Pasajero pasajero) {
		boolean alta = daoPasajero.altaPasajero(pasajero);
		return alta;
	}
	
	/**
	 * Método para borrar un objeto de tipo pasajero de la base de datos a partir del
	 * id del pasajero
	 * @param id el id del pasajero a borrar
	 * @return true en caso de que se haya borrado el pasajero, false en caso contrario
	 */
	
	public boolean borrar(int id) {
		boolean borrar = daoPasajero.borrarPasajero(id);
		return borrar;
	}
	
	/**
	 * Método que permite consultar un objeto de tipo pasajero guardado en la base de datos
	 * mediante su id
	 * @param id el id del pasajero a consultar
	 * @return el objeto de tipo pasajero si existe en la base de datos, null en caso contrario
	 */
	
	public Pasajero consultar(int id) {
		Pasajero pasajero = daoPasajero.consultarPasajero(id);
		
		return pasajero;
	}
	
	/**
	 * Método que devuelve un ArrayList con los pasajeros que existen en la base de datos
	 * @return la lista de pasajeros si están estos guardados en la base de datos,
	 * el arraylist vacío en caso de que no haya pasajeros en la base de datos, null en caso
	 * de error al abrir conexión o si se levantan excepciones
	 */
	
	public ArrayList<Pasajero> listar(){
		ArrayList<Pasajero> listaPasajeros = daoPasajero.listarPasajeros();
		return listaPasajeros;
	}
	
	/**
	 * Método que permite asignar un objeto de tipo pasajero a un objeto de tipo coche
	 * @param idPasajero el id del pasajero al que se le quiere añadir un coche
	 * @param idCoche el id del coche a asignar al pasajero
	 * @return true si se ha añadido, false en caso contrario
	 */
	public boolean añadirPasajero(int idPasajero, int idCoche) {
		boolean añadido = daoPasajero.addPasajeroCoche(idPasajero, idCoche);
		return añadido;
		
	}
	
	/**
	 * Método para eliminar un objeto de tipo pasajero de un objeto de tipo coche
	 * @param id el id del pasajero a eliminar del coche
	 * @return true si se ha eliminado el pasajero del coche, false en caso contrario
	 */
	public boolean eliminarPasajeroCoche(int id) {
		boolean eliminado = daoPasajero.eliminarPasajeroCoche(id);
		return eliminado;
	}
	
	/**
	 * Método que permite listar los pasajeros que tiene asignados un coche
	 * @param idCoche el id del coche cuyos pasajeros queremos listar
	 * @return la lista de pasajeros que tiene asignado el coche, el arraylist vacío en caso de que
	 * no tenga pasajeros asignados, null en caso de error al abrir conexión o si se levantan excepciones
	 */
	public ArrayList<Pasajero> listarPasajerosCoche(int idCoche){
		ArrayList<Pasajero> listaPasajeros = daoPasajero.listarPasajerosCoche(idCoche);
		return listaPasajeros;
	}
}
