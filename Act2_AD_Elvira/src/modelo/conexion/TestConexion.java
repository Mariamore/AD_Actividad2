package modelo.conexion;

import java.sql.Connection;

public class TestConexion {
	
	public static void main(String[] args) {
	    // Inicializamos la conexión
	    Conexion con = new Conexion();
	    con.inicializar();
	    
	    /*
	    
	    // Obtenemos los valores de la conexión desde el archivo de propiedades
	    String url = con.getProperty("url");
	    String user = con.getProperty("user");
	    String pwd = con.getProperty("pwd");
	    
	    // Imprimimos los valores obtenidos
	    System.out.println("URL: " + url);
	    System.out.println("Usuario: " + user);
	    System.out.println("Contraseña: " + pwd);*/
	    
	    // Probamos la conexión
	    Connection conn1 = Conexion.getConexion();
	    Connection conn2 = Conexion.getConexion();
	    Connection conn3 = Conexion.getConexion();
	    
	    // Verificamos que las conexiones sean iguales
	    if (conn1 == conn2 && conn2 == conn3) {
	        System.out.println("Todas las conexiones son iguales.");
	        System.out.println("Solo se ha establecido una conexión.");
	        System.out.println("La conexión es: " + conn1);
	    } else {
	        System.out.println("Error: Se han establecido múltiples conexiones.");
	    }
	}

		
	
	/*
	
	Puebas
	
	Connection conn1 = Conexion.getConexion();
	Connection conn2 = Conexion.getConexion();
	Connection conn3 = Conexion.getConexion();
	
	
	System.out.println(conn1);
	System.out.println(conn2);
	System.out.println(conn3);
	
	 */
	
	}
	
	 

