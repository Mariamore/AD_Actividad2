package modelo.persistencia.interfaces;

import java.util.ArrayList;

import modelo.entidad.Coche;

public interface DaoCoche {
	Boolean altaCoche(Coche coche);
	Boolean borrarCoche(int id);
	Coche consultarCoche(int id);
	Boolean modificarCoche(Coche coche);
	ArrayList<Coche> listarCoches();
	ArrayList<Coche> cochesConPasajeros();
	ArrayList<Coche> cochesDisponibles();
}
