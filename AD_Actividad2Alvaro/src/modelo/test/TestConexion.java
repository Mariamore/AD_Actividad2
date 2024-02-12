package modelo.test;

//import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.DaoCocheMySql;

public class TestConexion {

	public static void main(String[] args) {
		
		DaoCocheMySql daoCoche = new DaoCocheMySql();
		boolean conectado = daoCoche.abrirConexion();
		System.out.println(conectado);
		boolean desconectado = daoCoche.cerrarConexion();
		System.out.println(desconectado);

	}

}
