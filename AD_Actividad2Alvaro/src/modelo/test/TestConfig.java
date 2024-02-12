package modelo.test;

import modelo.persistencia.Config;

public class TestConfig {

	public static void main(String[] args) {
		
		Config config = new Config();
		config.iniciar();
		System.out.println(config.obtenerValor("url"));
		System.out.println(config.obtenerValor("user"));
		System.out.println(config.obtenerValor("pass"));

	}

}
