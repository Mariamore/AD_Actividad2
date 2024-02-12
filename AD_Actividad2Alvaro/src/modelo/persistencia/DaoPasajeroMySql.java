package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{
	
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
	public boolean altaPasajero(Pasajero pasajero) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		String sql = "insert into pasajeros (NOMBRE,EDAD,PESO) values (?,?,?)";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			int pasajerosAfectados = ps.executeUpdate();
			if (pasajerosAfectados == 0) {
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
	public boolean bajaPasajero(int idPasajero) {
		if(!abrirConexion()) {
			return false;
		}
		boolean baja = true;
		String sql = "delete from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idPasajero);
			int pasajerosAfectados = ps.executeUpdate();
			if(pasajerosAfectados == 0) {
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
	public Pasajero buscarPasajero(int idPasajero) {
		if(!abrirConexion()) {
			return null;
		}
		Pasajero pasajero = null;
		String sql = "select NOMBRE,EDAD,PESO from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idPasajero);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(idPasajero);
				pasajero.setNombre(rs.getString(1));
				pasajero.setEdad(rs.getInt(2));
				pasajero.setPeso(rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			cerrarConexion();
		}
		return pasajero;
	}

	@Override
	public List<Pasajero> listarPasajeros() {
		if(!abrirConexion()) {
			return null;
		}
		List<Pasajero> listaPasajeros = new ArrayList<Pasajero>();
		String sql = "select ID,NOMBRE,EDAD,PESO from pasajeros";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}

	@Override
	public boolean addPasajeroACoche(int idPasajero, int idCoche) {
		if (!abrirConexion()) {
			return false;
		}
		boolean pasajeroAdd = true;
		String sql = "update pasajeros set COCHE_ID=? where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			int pasajerosAfectados = ps.executeUpdate();
			if(pasajerosAfectados == 0) {
				pasajeroAdd = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
		return pasajeroAdd;
	}

	@Override
	public boolean eliminarPasajeroDeCoche(int idPasajero) {
		if (!abrirConexion()) {
			return false;
		}
		boolean pasajeroBorrado = true;
		String sql = "update pasajeros set COCHE_ID=null where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idPasajero);
			int pasajerosAfectados = ps.executeUpdate();
			if(pasajerosAfectados == 0) {
				pasajeroBorrado = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
		return pasajeroBorrado;
	}

	@Override
	public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
		if(!abrirConexion()) {
			return null;
		}
		List<Pasajero> listaPasajeros = new ArrayList<Pasajero>();
		String sql = "select ID,NOMBRE,EDAD,PESO from pasajeros where COCHE_ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idCoche);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}

}
