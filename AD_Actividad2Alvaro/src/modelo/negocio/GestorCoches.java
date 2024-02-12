package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;

public class GestorCoches {
	
	private DaoCocheMySql daoCoche = new DaoCocheMySql();
	
	
	/**
	 * Método que envía un coche para ser dado de alta en la base de datos.
	 * @param coche El coche que se enviará para dar de alta a la base de datos.
	 * @return True si el coche se ha podido dar de alta correctamente. 
	 * False si no se ha podido dar de alta.
	 */
	public boolean alta(Coche coche) {
		boolean alta = false;
		//Como regla de negocio, no pueden estar vacíos ni la marca ni el modelo del coche.
		//El método isBlank() asegura que ni esté vacío ni tenga solamente espacios en blanco.
		if(!coche.getMarca().isBlank() && !coche.getModelo().isBlank()) {
			alta = daoCoche.altaCoche(coche);
		}
		return alta;
	}
	
	/**
	 * Método que envía el id de un coche para ser dado de baja en la base de datos.
	 * @param idCoche El identificador unívoco del coche que se dará de baja en la base de datos.
	 * @return True si el coche se ha dado de baja correctamente de la base de datos. 
	 * False si no se ha podido dar de baja.
	 */
	public boolean baja(int idCoche) {
		boolean baja = daoCoche.bajaCoche(idCoche);
		return baja;
	}
	
	/**
	 * Método que envía un coche para ser buscado en la base de datos a partir de su id.
	 * @param idCoche El identificador unívoco del coche que será enviado a la base de datos 
	 * para obtener la información almacenada.
	 * @return Objeto de la clase Coche con la información que existe en la base de datos con el id 
	 * proporcionado.
	 */
	public Coche buscar(int idCoche) {
		Coche coche = daoCoche.buscarCoche(idCoche);
		return coche;
	}
	
	/**
	 * Método que envía un objeto de la clase Coche para ser modificado en la base de datos 
	 * a partir del id que existe en ese mismo objeto.
	 * @param coche Objeto de la clase coche con el id del coche a modificar.
	 * @return True si la modificación ha podido ser realizada. False si no se ha podido llevar 
	 * a cabo la modificación.
	 */
	public boolean modificar(Coche coche) {
		boolean modificado = daoCoche.modificarCoche(coche);
		return modificado;
	}
	
	/**
	 * Método que solicita toda la información existente de los coches de la base de datos.
	 * @return Objeto de la clase ArrayList con todos los coches de la base de datos. 
	 * Null si no se ha podido realizar la búsqueda.
	 */
	public List<Coche> listar(){
		List<Coche> listaCoches = daoCoche.listarCoches();
		return listaCoches;
	}

}
