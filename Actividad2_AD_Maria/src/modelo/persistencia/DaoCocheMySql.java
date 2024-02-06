package modelo.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.entidad.Coche;
import modelo.persistencia.conexion.Configuracion;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoCocheMySql implements DaoCoche{
	
	
	private ArrayList<Coche> listaCoches;
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public DaoCocheMySql() {
		
		listaCoches = new ArrayList<>();
		
		
	}
	
	/**
	 * Método que da de alta un objeto de tipo Coche en la base de datos
	 * @param coche el objeto Coche a dar de alta en la base de datos
	 * @return true en caso de que se haya dado de alta el objeto en la base de datos,
	 * false en caso contrario
	 */
	@Override
	public Boolean altaCoche(Coche coche) {
		if(!abrirConexion()){
			return false;
		}
		boolean creado = true;
		
		String query = "INSERT INTO coches (AÑO_FABRICACION, MARCA, MODELO, KMS) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, coche.getAñoFabricacion());
			ps.setString(2, coche.getMarca());
			ps.setString(3, coche.getModelo());
			ps.setDouble(4, coche.getKms());
			
			int numFilasafectadas = ps.executeUpdate();
			if (numFilasafectadas == 0)
				creado = false;
			
		} catch (SQLException e) {
			creado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return creado;
	}

	/**
	 * Método que borra un objeto de tipo Coche en la base de datos
	 * @param id el id del coche a borrar de la base de datos
	 * @return true en caso de que se de de bajo el objeto de tipo Coche en la BBDD,
	 * false en caso contrario
	 */
	@Override
	public Boolean borrarCoche(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		
		String query = "DELETE from coches where ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			int numFilasAfectadas = ps.executeUpdate();
			
			if (numFilasAfectadas==0)
				borrado = false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			borrado = false;
		}finally {
			cerrarConexion();
		}
		
		return borrado;
		
	}

	/**
	 * Método que permite consultar un coche que esté guardado en la base de datos a partir de 
	 * su id
	 * @param id del coche a consultar
	 * @return el objeto de tipo coche en caso de que estuviese guardado en la base de datos, 
	 * null en caso contrario
	 */
	@Override
	public Coche consultarCoche(int id) {
		if(!abrirConexion()){
			return null;
		}
		
		Coche c = null;
		
		String query = "SELECT * from coches where ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Coche();
				c.setId(rs.getInt(1));
				c.setAñoFabricacion(rs.getInt(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setKms(rs.getDouble(5));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		return c;
	}

	/**
	 * Método que permite modificar los atributos de un objeto de tipo coche
	 * @param coche el objeto coche con los nuevos valores de atributos a asignar al coche guardado en base de datos
	 * @return true en caso de que se haya modificado, false en caso contario
	 */
	@Override
	public Boolean modificarCoche(Coche coche) {
		if(!abrirConexion())
			return false;
		boolean modificado = true;
		
		String query = "Update coches set AÑO_FABRICACION = ?, MARCA = ?, MODELO = ?, KMS = ? WHERE ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, coche.getAñoFabricacion());
			ps.setString(2, coche.getMarca());
			ps.setString(3, coche.getModelo());
			ps.setDouble(4, coche.getKms());
			ps.setInt(5,coche.getId());
			
			int numFilasAfectadas = ps.executeUpdate();
			
			if (numFilasAfectadas== 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return modificado;
		
	}

	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos
	 * @return el arraylist con los coches guardados en la base de datos, el arraylist vacío,
	 * en caso de que no existan coches almacenados en la base de datos, null en caso de error
	 */
	@Override
	public ArrayList<Coche> listarCoches() {
		if(!abrirConexion())
			return null;
		
		ArrayList<Coche> listaCoches = new ArrayList<>();
		
		String query = "SELECT * FROM coches";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setAñoFabricacion(rs.getInt(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setKms(rs.getDouble(5));
				
				listaCoches.add(c);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return listaCoches;
		
	}

	
	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos
	 * que tengan asignado algún pasajero
	 * @return el arraylist con los coches con pasajeros asignados, guardados en la base de datos, 
	 * el arraylist vacío,en caso de que no existan coches almacenados en la base de datos que tengan asignados
	 * pasajeros, null en caso de error
	 */
	@Override
	public ArrayList<Coche> cochesConPasajeros() {
		if(!abrirConexion())
			return null;
		DaoPasajero daoPasajero = new DaoPasajeroMySql();
		
		ArrayList<Coche> listaCoches = new ArrayList<>();
		
		
		String query = "SELECT coches.id, coches.año_fabricacion, coches.marca, coches.modelo, coches.kms "
				+ "FROM coches "
				+ "INNER JOIN pasajeros ON coches.id = pasajeros.id_coche";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setAñoFabricacion(rs.getInt(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setKms(rs.getDouble(5));
				c.setP(daoPasajero.listarPasajerosCoche(rs.getInt(1)));
				
				if(!listaCoches.contains(c)) {
					listaCoches.add(c);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return listaCoches;
	}

	/**
	 * Método que permite listar los coches que se encuentren guardados en la base de datos que tengan plazas 
	 * disponibles 
	 * @return el arraylist con los coches con plazas libres, guardados en la base de datos, 
	 * el arraylist vacío,en caso de que no existan coches almacenados en la base de datos que tengan plazas libres,
	 * null en caso de error
	 */
	@Override
	public ArrayList<Coche> cochesDisponibles() {
		if(!abrirConexion())
			return null;
		
		ArrayList<Coche> listaCoches = new ArrayList<>();
		//Hemos determinado que estarán libres los coches que tengan asignados 4 o menos pasajeros
		String query = "SELECT coches.ID , coches.AÑO_FABRICACION, coches.MARCA, coches.MODELO, coches.KMS "
				+ "FROM coches "
				+ "LEFT JOIN pasajeros ON coches.ID = pasajeros.ID_COCHE "
				+ "GROUP BY coches.ID "
				+ "HAVING COUNT(pasajeros.ID_COCHE) < 5 OR COUNT(pasajeros.ID_COCHE) IS NULL";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setAñoFabricacion(rs.getInt(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setKms(rs.getDouble(5));
				
				if(!listaCoches.contains(c)) {
					listaCoches.add(c);
				}
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			cerrarConexion();
		}
		
		return listaCoches;
	}

	
}
