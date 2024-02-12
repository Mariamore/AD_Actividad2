package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySql implements DaoCoche {
	
	private Connection conexion;
	Config config = new Config();
	
	public boolean abrirConexion(){
		config.iniciar();
		String url = config.obtenerValor("url");
		String user = config.obtenerValor("user");
		String pass = config.obtenerValor("pass");
		try {
			conexion = DriverManager.getConnection(url,user,pass);
			//System.out.println("La conexión se ha realizado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
			//System.out.println("La conexión se ha cerrado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean altaCoche(Coche coche) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		String sql = "insert into coches (MARCA,MODELO,YEAR,KM) values (?,?,?,?)";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getYear());
			ps.setInt(4, coche.getKm());
			int cochesAfectados = ps.executeUpdate();
			if (cochesAfectados == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			alta = false;
		}finally {
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean bajaCoche(int idCoche) {
		if(!abrirConexion()) {
			return false;
		}
		boolean baja = true;
		String sql = "delete from coches where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idCoche);
			int cochesAfectados = ps.executeUpdate();
			if(cochesAfectados == 0) {
				baja = false;
			}
		} catch (SQLException e) {
			baja = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return baja;
	}

	@Override
	public Coche buscarCoche(int idCoche) {
		if(!abrirConexion()) {
			return null;
		}
		Coche coche = null;
		String sql = "select ID,MARCA,MODELO,YEAR,KM from coches where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idCoche);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setYear(rs.getInt(4));
				coche.setKm(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			cerrarConexion();
		}
		return coche;
	}

	@Override
	public boolean modificarCoche(Coche coche) {
		if (!abrirConexion()) {
			return false;
		}
		boolean modificado = true;
		String sql = "update coches set MARCA=?,MODELO=?,YEAR=?,KM=? where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getYear());
			ps.setInt(4, coche.getKm());
			ps.setInt(5, coche.getId());
			int cochesAfectados = ps.executeUpdate();
			if(cochesAfectados == 0) {
				modificado = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
		return modificado;
	}

	@Override
	public List<Coche> listarCoches() {
		if(!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<Coche>();
		String sql = "select ID,MARCA,MODELO,YEAR,KM from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setYear(rs.getInt(4));
				coche.setKm(rs.getInt(5));
				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
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
	public List<Coche> cochesConPasajeros() {
		if(!abrirConexion()) {
			return null;
		}
		DaoPasajeroMySql daoPasajero = new DaoPasajeroMySql();
		List<Coche> listaCoches = new ArrayList<>();
		String sql = "select COCHES.ID, COCHES.MARCA, COCHES.MODELO, COCHES.YEAR, COCHES.KM "
				+ "from coches "
				+ "inner join pasajeros on COCHES.ID = PASAJEROS.COCHE_ID";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setYear(rs.getInt(4));
				coche.setKm(rs.getInt(5));
				coche.setListaPasajeros(daoPasajero.listarPasajerosDeCoche(rs.getInt(1)));
				if(!listaCoches.contains(coche)) {
					listaCoches.add(coche);
				}
			}
		} catch (SQLException e) {
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
	public List<Coche> cochesDisponibles() {
		if(!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<>();
		//Hemos determinado que estarán libres los coches que tengan asignados 4 o menos pasajeros
		String query = "select COCHES.ID, COCHES.MARCA, COCHES.MODELO, COCHES.YEAR, COCHES.KM "
				+ "from COCHES "
				+ "left join PASAJEROS on COCHES.ID = PASAJEROS.COCHE_ID "
				+ "group by COCHES.ID "
				+ "having count(PASAJEROS.COCHE_ID) < 5 OR COUNT(PASAJEROS.COCHE_ID) is null";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setYear(rs.getInt(4));
				coche.setKm(rs.getInt(5));
				
				if(!listaCoches.contains(coche)) {
					listaCoches.add(coche);
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
