package modelo.persistencia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Config {
	
	private Properties properties;
	
	public void iniciar() {
		try (InputStream configuracion = Config.class.getClassLoader()
				.getResourceAsStream("config.properties");){
			properties = new Properties();
			properties.load(configuracion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String obtenerValor(String clave) {
		return properties.getProperty(clave);
	}

}
