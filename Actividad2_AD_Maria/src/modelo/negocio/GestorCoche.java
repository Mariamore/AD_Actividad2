package modelo.negocio;

import java.util.ArrayList;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {
	
private DaoCoche daoCoche = new DaoCocheMySql();

	/**
	 * Método que valida que el objeto de tipo coche que se le pasa tenga asignado una marca y un modelo
	 * @param coche el coche a validar
	 * @return false en caso de que la marca y/o el modelo del coche tengan valor null, true, en caso contrario
	 */
	public boolean validarCoche(Coche coche) {
		if(coche.getMarca() == "" || coche.getModelo() == "") {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método que permite dar de alta en la base de datos un objeto de tipo coche, validando antes
	 * que su modelo y/o marca no tengan valor null
	 * @param coche el objeto coche a dar de alta
	 * @return 3 si el modelo y/o la marca del coche tienen valor null, en caso contrario, 1 si se ha
	 * dado de alta, 2 si no se ha podido dar de alta
	 */
	public int altaCoche(Coche coche) {
		if(validarCoche(coche)) {
			boolean alta = daoCoche.altaCoche(coche);
			
			if (alta)
				return 1;
			else
				return 2;
		}
		else return 3;
	}
	/**
	 * Método que permite eliminar un coche de la base de datos a partir de su id
	 * @param id el id del coche a eliminar
	 * @return true, en caso de que se haya eliminado el coche de la base de datos, 
	 * false en caso contrario
	 */
	public boolean bajaCoche(int id) {
		boolean baja = daoCoche.borrarCoche(id);
		return baja;
	}
	
	/**
	 * Método que permite consultar un coche que esté guardado en la base de datos a partir de 
	 * su id
	 * @param id del coche a consultar
	 * @return el objeto de tipo coche en caso de que estuviese guardado en la base de datos, 
	 * null en caso contrario
	 */
	public Coche consultar(int id) {
		Coche coche = daoCoche.consultarCoche(id);
		
		return coche;
	}
	
	
	/**
	 * Método que permite modificar los atributos de un objeto de tipo coche, siempre y cuando 
	 * el modelo y/o la marca del mismo no tengan valor null
	 * @param coche el objeto coche con los nuevos valores de atributos a asignar al coche guardado en base de datos
	 * @return 3 si el modelo y/o la marca del coche tienen valor null, en caso contrario, 1 si se ha
	 * modificado, 2 si no se ha podido modificar
	 */
	public int modificar(Coche coche) {
		if(validarCoche(coche)) {
			boolean modificado = daoCoche.modificarCoche(coche);
			
			if (modificado)
				return 1;
			else
				return 2;
		}
		else return 3;
	}
	
	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos
	 * @return el arraylist con los coches guardados en la base de datos, el arraylist vacío,
	 * en caso de que no existan coches almacenados en la base de datos, null en caso de error
	 */
	public ArrayList<Coche> listar(){
		ArrayList<Coche> listaCoches = daoCoche.listarCoches();
		return listaCoches;
	}
	
	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos
	 * que tengan asignado algún pasajero
	 * @return el arraylist con los coches con pasajeros asignados, guardados en la base de datos, 
	 * el arraylist vacío,en caso de que no existan coches almacenados en la base de datos que tengan asignados
	 * pasajeros, null en caso de error
	 */
	public ArrayList<Coche> listarCochesConPasajero(){
		ArrayList<Coche> listaCoches = daoCoche.cochesConPasajeros();
		return listaCoches;
	}
	
	
	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos que tengan plazas 
	 * disponibles 
	 * @return el arraylist con los coches con plazas libres, guardados en la base de datos, 
	 * el arraylist vacío,en caso de que no existan coches almacenados en la base de datos que tengan plazas libres,
	 * null en caso de error
	 */
	public ArrayList<Coche> cochesDisponibles(){
		ArrayList<Coche> listaCoches = daoCoche.cochesDisponibles();
		return listaCoches;
	}
}
