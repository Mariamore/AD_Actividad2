package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.CochesConPasajeros;
import modelo.entidad.Pasajero;

public class CochesConPasajerosDaoImpl extends AbstractDao implements CochesConPasajerosDao{

	@Override
	public int asignarPasajeroCoche(int id, int idPa) {
			
		//añadimos coche a pasajero 
		//Preparamos la sentencia sql
		sql="insert into cochespasajeros (id_coche, id_pasajero) values (?,?)";
				
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, idPa);
			
			filas = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	@Override
	public int EliminarPasajeroDeCoche(int idPa) {
		//Preparamos sentencia
				sql="delete from cochespasajeros where id_pasajero=?";
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, idPa);
					filas= ps.executeUpdate();//vamos a modificar
					System.out.println("Coche elminado de la base de datos");
				} catch (SQLException e) {
					//Error
					System.out.println("No hemos podido dar de baja el coche con el id: " +  idPa);
					e.printStackTrace();
				}
				return filas;
		
		
	}

	

	@Override
	public List<CochesConPasajeros> listaCochesPasajeros() {
		//Preparamos sentencia
		sql="select * from cochespasajeros";
		List<CochesConPasajeros> lista = new ArrayList<CochesConPasajeros>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				CochesConPasajeros cp = new CochesConPasajeros();
				
				cp.setId(rs.getInt("id_pasajero"));
				cp.setId(rs.getInt("id_coche"));
				
				lista.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public List<Pasajero> pasajerosDelCoche(int id) {
		 sql = "select p.id, p.nombre, p.edad, p.peso from pasajeros p inner join cochespasajeros cp on p.id = cp.id_pasajero " +
                "where cp.id_coche = ?";
		 List<Pasajero> lp = new ArrayList<>();
		 
		 try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				
				while(rs.next()) {
				Pasajero p = new Pasajero();
				
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setEdad(rs.getInt("edad"));
					p.setPeso(rs.getDouble("peso"));
					
					lp.add(p);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lp;
		
	}

	

}
