package modelo.dao;

import java.util.List;

import modelo.entidad.Coche;

public interface CocheDao {
	
	int altaCoche(Coche coche);
	int eliminarCoche(int id);
	Coche buscarCoche (int id);
	int modificarCoche (Coche coche);
	List<Coche> listaCoches();
	
}
