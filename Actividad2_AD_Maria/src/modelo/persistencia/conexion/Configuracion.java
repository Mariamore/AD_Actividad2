package modelo.persistencia.conexion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	
private Properties properties;
	
	/**
	 * Método que nos permite cargar en un objeto de tipo properties los valores del fichero de propiedades
	 */
	public void inicializar() {
		
		try (InputStream ficheroPropiedades = Configuracion.class.getClassLoader().getResourceAsStream("config.properties");){
			
			properties = new Properties();
			
			properties.load(ficheroPropiedades);
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que devuelve el valor de una propiedad a partir de su clave
	 * @param key la clave de la propiedad
	 * @return valor de la propiedad, null en caso de que no exista.
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
