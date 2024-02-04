package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import modelo.entidad.Pasajero;
import modelo.persistencia.conexion.Configuracion;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{

	
	private ArrayList<Pasajero> listaPasajeros;
	
	private Configuracion conf;
	private Connection conn;
	
	/**
	 * Método que sirve para establecer la conexión con la BBDD
	 * @return true si se ha producido la conexión, false en caso contrario
	 */
	
	public boolean abrirConexion(){
		conf = new Configuracion();
		conf.inicializar();
		String url = conf.getProperty("url");
		String user = conf.getProperty("user");
		String pwd = conf.getProperty("pass");
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método que sirve para cerrar la conexión con la BBDD
	 * @return true si se ha podido cerrar la conexión con la BBDD, false en caso contrario
	 */
	
	public boolean cerrarConexion() {
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public DaoPasajeroMySql() {
		
		listaPasajeros = new ArrayList<>();
		
		
	}
	
	/**
	 * Método para dar de alta un objeto de tipo pasajero en la base de datos
	 * @param pasajero el objeto pasajero a dar de alta
	 * @return true si se ha dado de alta, false en caso contrario
	 */
	
	@Override
	public Boolean altaPasajero(Pasajero pasajero) {
		if(!abrirConexion())
			return true;
		boolean creado = true;
		
		String query = "INSERT INTO pasajeros (EDAD, NOMBRE, PESO) VALUES (?,?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, pasajero.getEdad());
			ps.setString(2, pasajero.getNombre());
			ps.setDouble(3, pasajero.getPeso());
			
			int numFilasfectadas = ps.executeUpdate();
			
			if(numFilasfectadas==0)
				creado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			creado = false;
			e.printStackTrace();
			
		}finally {
			cerrarConexion();
		}
		
		return creado;
	}

	/**
	 * Método para borrar un objeto de tipo pasajero de la base de datos a partir del
	 * id del pasajero
	 * @param id el id del pasajero a borrar
	 * @return true en caso de que se haya borrado el pasajero, false en caso contrario
	 */
	@Override
	public Boolean borrarPasajero(int id) {
		if(!abrirConexion())
			return false;
		
		boolean borrado = true;
		
		String query = "DELETE FROM pasajeros WHERE ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			int numFilasAfectadas = ps.executeUpdate();
			
			if (numFilasAfectadas==0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		return borrado;
	}

	
	/**
	 * Método que permite consultar un objeto de tipo pasajero guardado en la base de datos
	 * mediante su id
	 * @param id el id del pasajero a consultar
	 * @return el objeto de tipo pasajero si existe en la base de datos, null en caso contrario
	 */
	@Override
	public Pasajero consultarPasajero(int id) {
		if(!abrirConexion())
			return null;
		Pasajero p = null;
		DaoCoche daoCoche = new DaoCocheMySql();
		
		String query = "SELECT * FROM pasajeros WHERE ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setEdad(rs.getInt(2));
				p.setNombre(rs.getString(3));
				p.setPeso(rs.getDouble(4));
				p.setCoche(daoCoche.consultarCoche(rs.getInt(5)));
				;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}finally {
			cerrarConexion();
		}
		return p;
	}

	
	/**
	 * Método que devuelve un ArrayList con los pasajeros que existen en la base de datos
	 * @return la lista de pasajeros si están estos guardados en la base de datos,
	 * el arraylist vacío en caso de que no haya pasajeros en la base de datos, null en caso
	 * de error al abrir conexión o si se levantan excepciones
	 */
	@Override
	public ArrayList<Pasajero> listarPasajeros() {
		if(!abrirConexion())
			return null;
		
		ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
		DaoCoche daoCoche = new DaoCocheMySql();
		
		String query = "SELECT * FROM pasajeros";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Pasajero p = new Pasajero();
				
				p.setId(rs.getInt(1));
				p.setEdad(rs.getInt(2));
				p.setNombre(rs.getString(3));
				p.setPeso(rs.getDouble(4));
				p.setCoche(daoCoche.consultarCoche(rs.getInt(5)));
				
				listaPasajeros.add(p);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		} finally {
			cerrarConexion();
		}
		
		return listaPasajeros;
		
	} 
	
	/**
	 * Método que permite asignar un objeto de tipo pasajero a un objeto de tipo coche
	 * @param idPasajero el id del pasajero al que se le quiere añadir un coche
	 * @param idCoche el id del coche a asignar al pasajero
	 * @return true si se ha añadido, false en caso contrario
	 */
	@Override
	public Boolean addPasajeroCoche(int idPasajero, int idCoche) {
		if(!abrirConexion())
			return false;
		boolean pasajeroEnCoche = true;
		
		String query = "UPDATE pasajeros SET ID_COCHE = ? WHERE ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			
			int numFilasAfectadas = ps.executeUpdate();
			
			if(numFilasAfectadas==0)
				pasajeroEnCoche = false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			pasajeroEnCoche = false;
		}finally {
			cerrarConexion();
		}
		return pasajeroEnCoche;
	}

	/**
	 * Método para eliminar un objeto de tipo pasajero de un objeto de tipo coche
	 * @param id el id del pasajero a eliminar del coche
	 * @return true si se ha eliminado el pasajero del coche, false en caso contrario
	 */
	@Override
	public Boolean eliminarPasajeroCoche(int idPasajero) {
		if(!abrirConexion())
			return false;
		boolean borrado = true;
		String query ="update pasajeros "
				+ "set id_coche = NULL "
				+ "where id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idPasajero);
			
			int numFilasAfectadas = ps.executeUpdate();
			
			if(numFilasAfectadas==0)
				borrado = false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			borrado = false;
		}finally {
			cerrarConexion();
		}
		return borrado;
	}

	
	/**
	 * Método que permite listar los pasajeros que tiene asignados un coche
	 * @param idCoche el id del coche cuyos pasajeros queremos listar
	 * @return la lista de pasajeros que tiene asignado el coche, el arraylist vacío en caso de que
	 * no tenga pasajeros asignados, null en caso de error al abrir conexión o si se levantan excepciones
	 */
	@Override
	public ArrayList<Pasajero> listarPasajerosCoche(int idCoche) {
		if(!abrirConexion())
			return null;
		
		ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
		DaoCoche daoCoche = new DaoCocheMySql();
		
		String query = "SELECT * FROM pasajeros WHERE ID_COCHE = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Pasajero p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setEdad(rs.getInt(2));
				p.setNombre(rs.getString(3));
				p.setPeso(rs.getDouble(4));
				p.setCoche(daoCoche.consultarCoche(rs.getInt(5)));
				
				listaPasajeros.add(p);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		} finally {
			cerrarConexion();
		}
		
		return listaPasajeros;
	}
	
	
	
}
