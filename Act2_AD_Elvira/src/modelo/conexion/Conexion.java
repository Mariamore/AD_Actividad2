package modelo.conexion;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Properties properties;
    private static Connection conn;

    public static void inicializar() {
        try (InputStream ficheroPropiedades = Conexion.class.getClassLoader().getResourceAsStream("conf.properties")) {
            properties = new Properties();
            properties.load(ficheroPropiedades);
            System.out.println("Valores cargados desde el archivo: ");
            System.out.println("url: " + properties.getProperty("url"));
            System.out.println("user: " + properties.getProperty("user"));
            System.out.println("pwd: " + properties.getProperty("pwd"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        if (conn == null) {
            inicializar();
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("pwd");

            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("CONEXIÓN ESTABLECIDA");
            } catch (SQLException e) {
                System.out.println("CONEXIÓN NO ESTABLECIDA");
                e.printStackTrace();
            }
        }
        return conn;
    }
}

	/*
	
	//Conexion a la base de datos
	// Vamos a conectarnos a la base de datos 
	
			private static String url, user, pwd;
			private static Connection conn;
		//Todos los datos estaticos, porque el metodo tambien lo es.
			
			
			public Conexion() {
				
				url= "jdbc:mysql://localhost:3306/gestionConcesionario?serverTimezone=UTC";
				user="root";
				pwd="root";
				
				try {
					conn = DriverManager.getConnection(url, user, pwd);
					System.out.println("CONEXION ESTABLECIDAD");
				} catch (SQLException e) {
					System.out.println("CONEXION NO ESTABLECIDA");
					e.printStackTrace();
				}
			}
			
			public static Connection getConexion() {
				if (conn==null) {
					new Conexion();
				}
				return conn;
			}
			*/
	
	
			


