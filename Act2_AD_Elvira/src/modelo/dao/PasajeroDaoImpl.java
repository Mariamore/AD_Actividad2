package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.entidad.Pasajero;

public class PasajeroDaoImpl extends AbstractDao implements PasajeroDao {

	@Override
	public int altaPasajero(Pasajero p) {
		//Preparamos la sentencia sql
		sql="insert into pasajeros values (?,?,?,?)";
		try {
			 ps =conn.prepareStatement(sql);
			 ps.setInt(1, p.getId());
			 ps.setString(2, p.getNombre());
			 ps.setInt(3, p.getEdad());
			 ps.setDouble(4, p.getPeso());
			 
			 filas = ps.executeUpdate();
		} catch (SQLException e) {
			//ERROR
			System.out.println("No se ha podido dar de alta al pasajero");
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int eliminarPasajero(int id) {
		//Preparamos sentencia
		sql="delete from pasajeros where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			filas= ps.executeUpdate();//vamos a modificar
			System.out.println("Pasajero elminado de la base de datos");
		} catch (SQLException e) {
			//Error
			System.out.println("No hemos podido dar de baja al pasajero con el id: " +  id);
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public Pasajero buscarPasajero(int id) {
		
		//Preparamos la sentencia
				sql="select * from pasajeros where id=?";
				Pasajero p = null;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					rs = ps.executeQuery(); //Ejecuamos la query 
					
					while(rs.next()) {
						
						p = new Pasajero();
						p.setId(rs.getInt("id"));		
						p.setNombre(rs.getString("nombre"));
						p.setEdad(rs.getInt("edad"));
						p.setPeso(rs.getDouble("peso"));
					
						}
					
				} catch (SQLException e) {
					
					System.out.println("Error en el pasajero con el id: "+ id + " no ha sido encontrado");
					e.printStackTrace();
				}
			
				return p;//retornamos el pasajero
	}

	@Override
	public List<Pasajero> listaPasajeros() {
		//Preparamos sentecias
				sql="select * from pasajeros";
				List<Pasajero> lista = new ArrayList<Pasajero>();//Creamos una lista
				Pasajero p = null;
				try {
					
					ps=conn.prepareStatement(sql);
					rs= ps.executeQuery();
				
					while(rs.next()){ 
						p = new Pasajero();; //construimos otra vez el objeto
						
						p.setId(rs.getInt("id"));		
						p.setNombre(rs.getString("nombre"));
						p.setEdad(rs.getInt("edad"));
						p.setPeso(rs.getDouble("peso"));
						
						lista.add(p);//Añadimos a la lista
					}
			
				} catch (SQLException e) {
				e.printStackTrace();
				} 
		return lista;
	}

}
