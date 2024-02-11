package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.conexion.Conexion;

public abstract class AbstractDao {
	
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected String sql;
	protected int filas;
	protected String filas2; //Añadimos otra variable de tipo String
	
	public AbstractDao() {
		
		conn = Conexion.getConexion();
		if (conn == null) {
	        System.out.println("La conexión no se ha inicializado correctamente en AbstractDao.");
	    }
	
	}
	
	

}
