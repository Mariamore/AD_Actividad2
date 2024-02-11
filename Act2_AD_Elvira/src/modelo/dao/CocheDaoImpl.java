package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;


public class CocheDaoImpl extends  AbstractDao implements CocheDao{

	@Override
	public int altaCoche(Coche coche) {
		//Preparamos sentencia sql
		// Cuantos campo tenemos para dar de alta??
		sql= "insert into coches values(?,?,?,?,?)";
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1,coche.getId());
			ps.setString(2, coche.getMarca());
			ps.setString(3, coche.getModelo());
			ps.setInt(4, coche.getAñofabricacion());
			ps.setDouble(5, coche.getKm());
			
			filas = ps.executeUpdate();//Update porque queremos modificar
			
			System.out.println("Coche añadido");
		} catch (SQLException e) {
			//ERROR
			System.out.println("No se ha podido dar de alta el coche");
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int eliminarCoche(int id) {
		//Preparamos sentencia
		sql="delete from coches where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			filas= ps.executeUpdate();//vamos a modificar
			System.out.println("Coche elminado de la base de datos");
		} catch (SQLException e) {
			//Error
			System.out.println("No hemos podido dar de baja el coche con el id: " +  id);
			e.printStackTrace();
		}
		return filas;
		
	}

	@Override
	public Coche buscarCoche(int id) {
		
		//Preparamos la sentencia
		sql="select * from coches where id=?";
		Coche ch = null;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery(); //Ejecuamos la query 
			
			while(rs.next()) {
				
				ch = new Coche();
				ch.setId(rs.getInt("id"));		
				ch.setMarca(rs.getString("marca"));
				ch.setModelo(rs.getString("modelo"));
				ch.setAñofabricacion(rs.getInt("añofabricacion"));
				ch.setKm(rs.getDouble("km"));
				}
			
		} catch (SQLException e) {
			
			System.out.println("Error el coche con el id: "+ id + " no ha sido encontrado");
			e.printStackTrace();
		}
	
		return ch;//retornamos el coche
	}

	@Override
	public int modificarCoche(Coche coche) {
		//Preparamos sentencia
		
		sql="update coches set marca=?, modelo=?, añofabricacion=?, km=? where id=?";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getAñofabricacion());
			ps.setDouble(4, coche.getKm());
			filas = ps.executeUpdate(); //modificamos 
	
			
		} catch (SQLException e) {
			
			System.out.println("Error el coche no ha podido ser modificado");
			e.printStackTrace();
		}
		
		return filas;
		
	}


	@Override
	public List<Coche> listaCoches() {
		//Preparamos sentecias
		sql="select * from coches";
		List<Coche> lista = new ArrayList<Coche>();//Creamos una lista
		Coche ch = null;
		try {
			
			ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
		
			while(rs.next()){ 
				 ch = new Coche(); //construimos otra vez el objeto
				
				ch.setId(rs.getInt("id"));
				ch.setMarca(rs.getString("marca"));
				ch.setModelo(rs.getString("modelo"));
				ch.setAñofabricacion(rs.getInt("añofabricacion"));
				ch.setKm(rs.getDouble("km"));
				
				lista.add(ch);//Añadimos a la lista
			}
	
		} catch (SQLException e) {
		e.printStackTrace();
		} 
		return lista;
		
	}
	
	
}
